package lamas.brights.eshop.order;


import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
