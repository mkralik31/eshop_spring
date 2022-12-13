package lamas.brights.eshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), List.of()
        );
    }

    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.save(new User (user.getFirstName(), user.getLastName(), user.getStreetName(), user.getStreetNo(),
                user.getCity(), user.getPostalCode(), user.getPhoneNo(), user.getEmail(), user.getUsername(), encodedPassword));
    }


}
