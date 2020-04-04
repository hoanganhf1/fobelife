package vn.com.fobelife.component.cart.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.cart.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUsername(String username, Sort sort);
    Order findByCode(String code);
    List<Order> findAll(Sort sort);
}
