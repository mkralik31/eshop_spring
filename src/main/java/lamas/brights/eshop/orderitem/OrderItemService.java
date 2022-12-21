package lamas.brights.eshop.orderitem;

import lamas.brights.eshop.order.Order;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> orderItems(Order order);

}
