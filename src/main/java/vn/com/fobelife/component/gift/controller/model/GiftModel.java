package vn.com.fobelife.component.gift.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.product.dto.ProductDto;

@Getter
@Setter
public class GiftModel {

    public static final String NAME = "mGift";

    private String pageType;
    private List<ProductDto> data;
    private Integer point;
}
