package lamas.brights.eshop.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Cart(Integer quantity, double price, Date createDate, User user, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.createDate = createDate;
        this.user = user;
        this.product = product;
    }

    public Cart() {
    }

    public Cart(Product product, Integer quantity, User user) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }
}
