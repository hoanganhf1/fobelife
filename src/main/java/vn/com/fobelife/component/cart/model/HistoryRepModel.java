package vn.com.fobelife.component.cart.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.cart.dto.OrderDto;

@Getter
@Setter
public class HistoryRepModel {

    public static final String NAME = "mHistory";
    private List<OrderDto> data;
    private boolean admin;
}
