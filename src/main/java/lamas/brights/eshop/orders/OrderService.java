package lamas.brights.eshop.orders;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrdersById(long id);
}
