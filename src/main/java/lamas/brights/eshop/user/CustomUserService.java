package lamas.brights.eshop.user;

import lamas.brights.eshop.user.authorization.Login;

import lamas.brights.eshop.user.authorization.RegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService {

    void register(RegistrationDTO registrationDTO);

    boolean emailExists(String email);

    Login login(String email, String password);

}
