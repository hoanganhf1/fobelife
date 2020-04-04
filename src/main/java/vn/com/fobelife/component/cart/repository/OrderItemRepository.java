package vn.com.fobelife.component.cart.repository;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.cart.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
