package lamas.brights.eshop.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    public OrderController (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/user/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
        // method to return order by id from database
        Order order = orderService.getOrderById(id);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


/*    @GetMapping("user/order")
    public ResponseEntity<Order> getOrderByUserId
*/

}
