package vn.com.fobelife.component.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String code;

    private String name;

    private String description;

    private String image;

    private Integer price;

    private String type;

    private String status;
}
