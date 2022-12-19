package lamas.brights.eshop.user.address;

import lamas.brights.eshop.user.User;

import java.util.List;

public interface AddressService {

    List<Address> getAddressForUser(User user);

    void createAddress(User user, Address address);
}
