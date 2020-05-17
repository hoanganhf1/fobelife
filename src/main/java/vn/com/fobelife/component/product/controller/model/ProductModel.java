package vn.com.fobelife.component.product.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.product.dto.ProductDto;

@Getter
@Setter
public class ProductModel {

    public static final String NAME = "mProduct";

    private String pageType;
    private List<ProductDto> data;
    private String total = "0";
    private Integer point;
    private Integer pointUsed;
}
