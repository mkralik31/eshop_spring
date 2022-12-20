package lamas.brights.eshop.order;


import lamas.brights.eshop.cart.CartService;
import lamas.brights.eshop.orderitem.OrderItemRepository;
import lamas.brights.eshop.user.User;
import lamas.brights.eshop.dto.CartDto;
import lamas.brights.eshop.dto.CartItemDto;
import lamas.brights.eshop.orderitem.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
    }

    @Override
    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByOrderDateDesc(user);
    }

    @Override
    public void createOrder(User user, Order order) {

        System.err.println(order.getTotalPrice());
        // get items from user's cart
        CartDto cartDto = cartService.listCartItems(user);

        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

        // create newOrder and save it
        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setUser(user);
        newOrder.setTotalPrice(order.getTotalPrice());
        orderRepository.save(newOrder);

        System.err.println(
                "\n" +"OrderServiceImpl.createOrder" + "\n" +
                        newOrder.getOrderId() + "\n" +
                        newOrder.getOrderDate() + "\n" +
                        newOrder.getTotalPrice() + "\n" );

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save it for every item
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemRepository.save(orderItem);

            System.err.println(
                    "\n" +"OrderServiceImpl create item in order" + "\n" +
                            "orderItemId: " + orderItem.getId() + "\n" +
                            "orderId: " + orderItem.getOrder().getOrderId() + "\n" +
                            "productName: " + orderItem.getProduct().getName() + "\n" +
                            "productQuantity: " + orderItem.getQuantity() + "\n" );
        }
        // when all items are saved in orderItem table, delete items from cart table
        cartService.deleteUserCartItems(user);
    }

}
