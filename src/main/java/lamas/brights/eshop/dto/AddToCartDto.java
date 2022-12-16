package lamas.brights.eshop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddToCartDto {
    private Long id;
    private long productId;
    private Integer quantity;

    public AddToCartDto() {
    }
}
