package lamas.brights.eshop.order;

;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lamas.brights.eshop.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;


    public Order() {
    }

}
