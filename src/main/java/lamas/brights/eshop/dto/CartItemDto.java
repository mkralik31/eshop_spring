package lamas.brights.eshop.dto;

import lamas.brights.eshop.cart.Cart;
import lamas.brights.eshop.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CartItemDto {

    private Long id;
    private Integer quantity;
    private Product product;

    public CartItemDto() {
    }

    public CartItemDto(Long id, Integer quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    //this will fix null value for getting items to cart
    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
