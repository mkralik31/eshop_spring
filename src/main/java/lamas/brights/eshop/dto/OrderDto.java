package lamas.brights.eshop.dto;

import lamas.brights.eshop.orderitem.OrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDto {

    private Long orderId;

    private LocalDate orderDate;

    private double totalPrice;

    private List<OrderItem> orderItemList;

    public OrderDto(Long orderId, LocalDate orderDate, double totalPrice, List<OrderItem> orderItemList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }

}
