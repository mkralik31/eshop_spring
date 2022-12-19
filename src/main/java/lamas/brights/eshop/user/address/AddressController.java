package lamas.brights.eshop.user.address;

import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AddressController(AddressService addressService,
                             AuthenticationService authenticationService) {
        this.addressService = addressService;
        this.authenticationService = authenticationService;
    }
    @GetMapping("/user/address")
    public ResponseEntity<List<Address>> getAddressForUser(@RequestParam("token") String token) throws Exception {
        if (token != null) {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            List<Address> addresses = addressService.getAddressForUser(user);
            if (addresses == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/user/address/add")
    public ResponseEntity<String> createAddress(@RequestParam("token") String token,
                                              @RequestBody Address address) throws Exception {
        // validate token (if TOKEN, ORDER and USER is present)
        if (token != null && address != null) {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            // place the order
            addressService.createAddress(user, address);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
