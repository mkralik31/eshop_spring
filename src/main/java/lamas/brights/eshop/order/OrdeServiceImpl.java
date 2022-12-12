package lamas.brights.eshop.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdeServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrdersById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        Order order = null;

        if(optional.isPresent()){
            order = optional.get();
        }else{
            throw new RuntimeException("Orders not found for id: " + id);
        }

        return order;
    }
}
