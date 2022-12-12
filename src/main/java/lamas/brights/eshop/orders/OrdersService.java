package lamas.brights.eshop.orders;

import lamas.brights.eshop.product.Product;

import java.util.List;

public interface OrdersService {

    List<Orders> getAllOrders();

    Orders getOrdersById(long id);
}
