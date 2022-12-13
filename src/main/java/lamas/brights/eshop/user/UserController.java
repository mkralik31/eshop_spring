package lamas.brights.eshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/api")
public class UserController {

    private final CustomUserService customUserService;

    @Autowired
    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<RegistrationDTO> registerUser(@RequestBody RegistrationDTO registrationDTO) {

        // return conflict status because email is already in use
        if (customUserService.emailExists(registrationDTO.email())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        //this will handle error with passwords not matching
        if (!Objects.equals(registrationDTO.password(), registrationDTO.repeatPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // create user and return status 201 CREATED
        customUserService.register(registrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody LoginDTO loginDTO) {

        User user = customUserService.login(loginDTO.email(), loginDTO.password());
        if(user != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        //email or password is wrong
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}