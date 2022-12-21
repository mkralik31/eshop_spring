package lamas.brights.eshop.orderitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lamas.brights.eshop.order.Order;
import lamas.brights.eshop.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private double price;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public OrderItem(Long id, Integer quantity, double price, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public OrderItem() {

    }
}
