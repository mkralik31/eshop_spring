package lamas.brights.eshop.orderitem;

import lamas.brights.eshop.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> getOrderItemsByOrder(Order order);

}
