package vn.com.fobelife.component.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderReturnDto {

    private String transactionInfo;
    private Integer price;
    private Integer paymentId;
    private Integer paymentType;
    private String errorText;
    private String secureCode;
    private String tokenNl;
    private String orderCode;
}
