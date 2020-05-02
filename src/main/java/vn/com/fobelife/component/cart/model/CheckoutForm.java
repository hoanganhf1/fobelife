package vn.com.fobelife.component.cart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutForm {

    private String[] productOrder;
    private String cartTotal;
    private String paymentType;
    private String note;
}
