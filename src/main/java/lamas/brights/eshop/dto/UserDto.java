package lamas.brights.eshop.dto;

import lamas.brights.eshop.user.address.Address;

import java.util.List;

public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addressList;

    public UserDto(String firstName, String lastName, String email, List<Address> addressList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressList = addressList;
    }

    public UserDto() {
    }
}
