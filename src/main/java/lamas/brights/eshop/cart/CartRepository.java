package lamas.brights.eshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lamas.brights.eshop.user.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserOrderByCreateDateDesc(User user);

    //delete all items from cart by user (order impl)
    void deleteAllByUser(User user);
}
