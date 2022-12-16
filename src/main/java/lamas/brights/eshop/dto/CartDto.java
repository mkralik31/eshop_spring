package lamas.brights.eshop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartDto {

    private List<CartItemDto> cartItems;

    private double totalPrice;

    public CartDto() {
    }

    public CartDto(List<CartItemDto> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
}
