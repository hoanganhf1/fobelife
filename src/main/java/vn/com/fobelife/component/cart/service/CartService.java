package vn.com.fobelife.component.cart.service;

import java.util.List;

import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.dto.OrderReturnDto;
import vn.com.fobelife.component.cart.dto.OrderStatus;

public interface CartService {

    OrderDto createOrder(OrderDto dto) throws Exception;

    OrderDto submitGift(String productCode) throws Exception;

    OrderDto updateOrder(OrderReturnDto dto) throws Exception;

    List<OrderDto> getHistory(String username) throws Exception;

    OrderDto updateStatus(Long id, OrderStatus status) throws Exception;
}
