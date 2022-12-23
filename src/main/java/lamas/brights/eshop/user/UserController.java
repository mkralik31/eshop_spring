package lamas.brights.eshop.user;

import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.dto.LoginDto;
import lamas.brights.eshop.dto.LoginResponseDto;
import lamas.brights.eshop.dto.RegistrationDto;
import lamas.brights.eshop.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/api")
public class UserController {

    private final CustomUserService customUserService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(CustomUserService customUserService, AuthenticationService authenticationService) {
        this.customUserService = customUserService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/user/register")
    public ResponseEntity<RegistrationDto> registerUser(@RequestBody RegistrationDto registrationDTO) {

        // return conflict status because email is already in use
        if (customUserService.emailExists(registrationDTO.email())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        //this will handle error with passwords not matching
        if (!Objects.equals(registrationDTO.password(), registrationDTO.repeatPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // create user and return status 201 CREATED
            customUserService.register(registrationDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDTO) {

        LoginResponseDto  loginResponseDto = customUserService.login(loginDTO.email(), loginDTO.password());

        if(loginResponseDto != null) {
            return new ResponseEntity<>(loginResponseDto, HttpStatus.ACCEPTED);
        }
        //email or password is wrong
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserDetails(@RequestParam("token") String token) throws Exception {

        if (token != null) {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);

            UserDto userDto = customUserService.getUserDetails(user);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
