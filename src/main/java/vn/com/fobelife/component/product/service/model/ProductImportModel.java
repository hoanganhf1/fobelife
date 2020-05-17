package vn.com.fobelife.component.product.service.model;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.Getter;

@Getter
public class ProductImportModel {

    private String code;
    private String image;
    private String name;
    private String type;
    private Integer price;
    private String description;
    private String status;
    private Integer step;
    private Integer bonus;

    public ProductImportModel(String[] properties) {
        this.code = properties[0];
        this.image = properties[1];
        this.name = properties[2];
        this.description = properties[3];
        this.price = NumberUtils.toInt(properties[4]);
        this.type = properties[5].toUpperCase();
        this.status = properties[6].toLowerCase();
        this.step = NumberUtils.toInt(properties[7]);
        this.bonus = NumberUtils.toInt(properties[8]);
    }

}
