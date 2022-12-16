package lamas.brights.eshop.user;

import lamas.brights.eshop.dto.LoginResponseDto;
import lamas.brights.eshop.dto.RegistrationDto;

public interface CustomUserService {

    void register(RegistrationDto registrationDTO);

    boolean emailExists(String email);

    LoginResponseDto login(String email, String password);

}
