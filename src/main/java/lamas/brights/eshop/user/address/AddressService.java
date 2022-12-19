package lamas.brights.eshop.user.address;

import lamas.brights.eshop.user.User;

public interface AddressService {

    Address getAddressForUser(User user);

    void createAddress(User user, Address address);
}
