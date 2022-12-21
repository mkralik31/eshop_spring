package lamas.brights.eshop.user;

import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.authorization.AuthenticationToken;
import lamas.brights.eshop.dto.LoginResponseDto;
import lamas.brights.eshop.dto.RegistrationDto;
import lamas.brights.eshop.dto.UserDto;
import lamas.brights.eshop.user.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final AddressService addressService;

    @Autowired
    public CustomUserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationService authenticationService,
            AddressService addressService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.addressService = addressService;
    }

    public void register(RegistrationDto registrationDTO) {
        String encodedPassword = passwordEncoder.encode(registrationDTO.password());
        User newUser = null;
        newUser = userRepository.save(new User(
                registrationDTO.firstName(),
                registrationDTO.lastName(),
                registrationDTO.email(),
                encodedPassword));

        AuthenticationToken authenticationToken = new AuthenticationToken(newUser);
        authenticationService.saveConfirmationToken(authenticationToken);

    }

    @Override
    public boolean emailExists(String email) {
        //return true if email is already in use by some user
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public LoginResponseDto login(String email, String password) {
        // find user by email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        // check if input password is matching password that is stored in DB
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        //return token if the email and password are correct

        AuthenticationToken authenticationToken = authenticationService.getToken(user);
        System.err.println(authenticationToken.getToken());
        return new LoginResponseDto("success", authenticationToken.getToken());
    }

    @Override
    public UserDto getUserDetails(User user) {
        UserDto userDto = new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                addressService.getAddressForUser(user)
        );
        return userDto;
    }

}
