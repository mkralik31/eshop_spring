package lamas.brights.eshop.dto;


import lamas.brights.eshop.order.Order;
import lamas.brights.eshop.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderItemDto {

    private Integer quantity;

    private double price;

    private Order order;

    private Product product;

    public OrderItemDto(Integer quantity, double price, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public OrderItemDto(Order order) {
    }


}
