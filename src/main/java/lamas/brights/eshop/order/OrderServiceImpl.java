package lamas.brights.eshop.order;


import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByOrderDateDesc(user);
    }

    @Override
    public Order createOrder(Order order) {

        order.setOrderDate(new Date());

        System.err.println(
                "\n" +"OrderServiceImpl.createOrder" + "\n" +
                        order.getOrderId() + "\n" +
                        order.getOrderDate() + "\n" +
                        order.getTotalPrice() + "\n" );

        return orderRepository.save(order);
    }
}
