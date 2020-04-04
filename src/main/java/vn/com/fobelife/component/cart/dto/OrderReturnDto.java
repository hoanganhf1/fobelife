package vn.com.fobelife.component.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderReturnDto {

    private String transactionInfo;
    private int price;
    private int paymentId;
    private int paymentType;
    private String errorText;
    private String secureCode;
    private String tokenNl;
    private String orderCode;
}
