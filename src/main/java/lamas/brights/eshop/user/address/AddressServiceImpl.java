package lamas.brights.eshop.user.address;

import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAddressForUser(User user) {
        return addressRepository.findAddressByUser(user);
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
}
