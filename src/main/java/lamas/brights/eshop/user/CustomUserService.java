package lamas.brights.eshop.user;

import lamas.brights.eshop.dto.LoginResponseDto;
import lamas.brights.eshop.dto.RegistrationDto;
import lamas.brights.eshop.dto.UserDto;

public interface CustomUserService {

    void register(RegistrationDto registrationDTO);

    boolean emailExists(String email);

    LoginResponseDto login(String email, String password);

    UserDto getUserDetails(User user);

}
