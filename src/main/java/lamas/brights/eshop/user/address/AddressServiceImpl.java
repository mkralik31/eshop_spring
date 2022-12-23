package lamas.brights.eshop.user.address;

import lamas.brights.eshop.order.Order;
import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddressForUser(User user) {
        return addressRepository.findAddressesByUser(user);
    }

    @Override
    public void createAddress(User user, Address address) {

        // create newOrder and save it
        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setStreetName(address.getStreetName());
        newAddress.setStreetNumber(address.getStreetNumber());
        newAddress.setPostCode(address.getPostCode());
        newAddress.setUser(user);
        addressRepository.save(newAddress);

        System.err.println(
                "\n" +"AddressServiceImpl.createAddress" + "\n" +
                        "City: " + newAddress.getCity() + "\n" +
                        "Street name: " + newAddress.getStreetName() + "\n" +
                        "Street no: " + newAddress.getStreetNumber() + "\n" +
                        "Postal code: " + newAddress.getPostCode() + "\n" +
                        "UserId: " + newAddress.getUser().getUserId()
        );
    }

    @Override
    public Address getAddressForOrder(Long addressId) {
        return addressRepository.findAddressById(addressId);
    }
}
