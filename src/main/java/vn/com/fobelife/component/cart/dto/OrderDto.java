package vn.com.fobelife.component.cart.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private String code;
    private String type;
    private String status;
    private String username;
    private String total;
    private String transactionInfo;
    private List<OrderItemDto> items;
    private String itemsAsString;
    private String createdDate;
}
