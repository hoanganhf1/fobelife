package vn.com.fobelife.component.product.service.impl;

import org.apache.commons.lang3.math.NumberUtils;


public class ProductImportModel {

    private String[] properties;


    public ProductImportModel(String[] line) {
        this.properties = line;
    }

    public String getCode() {
        return properties[0];
    }

    public String getImage() {
        return properties[1];
    }

    public String getName() {
        return properties[2];
    }

    public String getType() {
        return properties[5].toUpperCase();
    }

    public Integer getPrice() {
        return NumberUtils.toInt(properties[4]);
    }

    public String getDescription() {
        return properties[3];
    }
}
