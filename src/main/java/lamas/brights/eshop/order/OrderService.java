package lamas.brights.eshop.order;

import lamas.brights.eshop.user.User;

import java.util.List;

public interface OrderService {

    List<Order> listOrders(User user);

}
