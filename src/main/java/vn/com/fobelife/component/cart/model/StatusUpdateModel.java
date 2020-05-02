package vn.com.fobelife.component.cart.model;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.cart.dto.OrderStatus;

@Getter
@Setter
public class StatusUpdateModel {

    private Long orderId;
    private OrderStatus status;
}
