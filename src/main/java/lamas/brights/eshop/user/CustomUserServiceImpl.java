package lamas.brights.eshop.user;

import lamas.brights.eshop.user.authorization.Login;
import lamas.brights.eshop.user.authorization.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final String accessTokenSecret;

    @Autowired
    public CustomUserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${application.security.access-token-secret}")String accessTokenSecret) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public void register(RegistrationDTO registrationDTO) {
        String encodedPassword = passwordEncoder.encode(registrationDTO.password());
        userRepository.save(new User (
                registrationDTO.firstName(),
                registrationDTO.lastName(),
                registrationDTO.email(),
                encodedPassword));
    }

    @Override
    public boolean emailExists(String email) {
        //return true if email is already in use by some user
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Login login(String email, String password) {
        // find user by email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        // check if input password is matching password that is stored in DB
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        //return token if the email and password are correct
        return Login.of(user.getUserId(),  accessTokenSecret);
    }

}
