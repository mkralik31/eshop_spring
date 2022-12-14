package lamas.brights.eshop.order;


import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import lamas.brights.eshop.user.User;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final AuthenticationService authenticationService;

    @Autowired
    public OrderController(OrderService orderService,
                           AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    //get all orders for user
    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam("token") String token) throws Exception {
        // validate token (if TOKEN and USER is present)
        if (token != null) {
            authenticationService.authenticate(token);

            // retrieve user after validation
            User user = authenticationService.getUser(token);

            // get orders for authenticated user
            List<OrderDto> orderDtoList = orderService.listOrderDto(user);

            return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/order/add")
    public ResponseEntity<String> createOrder(@RequestParam("token") String token,
                                              @RequestBody Order order) throws Exception {
        // validate token (if TOKEN, ORDER and USER is present)
        if (token != null && order != null) {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            // place the order
            System.err.println(order.getAddressId());
            orderService.createOrder(user, order);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
