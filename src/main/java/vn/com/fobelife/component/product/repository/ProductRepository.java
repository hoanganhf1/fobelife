package vn.com.fobelife.component.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByStatus(String status);
    List<Product> findByStatusAndTypeOrderByPriceAsc(String status, String type);
    Product findByCode(String code);
}
