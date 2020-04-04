package vn.com.fobelife.component.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private String productCode;
    private Integer quantity;
}
