package lamas.brights.eshop.orderitem;

import lamas.brights.eshop.dto.OrderItemDto;
import lamas.brights.eshop.order.Order;
import lamas.brights.eshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<OrderItem> orderItems(Order order) {
        return orderItemRepository.getOrderItemsByOrder(order);
    }
}
