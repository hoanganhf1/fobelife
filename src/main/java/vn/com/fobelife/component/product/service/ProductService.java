package vn.com.fobelife.component.product.service;

import java.util.List;

import vn.com.fobelife.component.product.dto.ProductDto;

public interface ProductService {

    ProductDto save(ProductDto dto) throws Exception;

    void importProduct(String csvContent) throws Exception;

    List<ProductDto> getAll() throws Exception;

    List<ProductDto> getByStatus(String status) throws Exception;
    List<ProductDto> getByStatusAndType(String status, String type) throws Exception;

    ProductDto updateStatus(String code, String status) throws Exception;
}
