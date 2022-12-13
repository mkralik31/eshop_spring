package lamas.brights.eshop.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService {

    void register(RegistrationDTO registrationDTO);

    boolean emailExists(String email);

    User login(String email, String password);
}
