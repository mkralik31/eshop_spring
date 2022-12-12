package lamas.brights.eshop.order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrdersById(long id);
}
