package vn.com.fobelife.component.product.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private String name;
    private String description;
    private Integer price;
    private String image;
    private String status;
}
