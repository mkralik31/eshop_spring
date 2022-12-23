package lamas.brights.eshop.user.address;

import lamas.brights.eshop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByUser(User user);

    List<Address> findAddressesByUser(User user);

    Address findAddressById(Long addressId);
}
