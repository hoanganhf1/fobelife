package vn.com.fobelife.component.cart.service;

import java.util.List;

import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.dto.OrderReturnDto;

public interface CartService {

    OrderDto createOrder(OrderDto dto) throws Exception;

    OrderDto submitGift(String productCode) throws Exception;

    OrderDto updateOrder(OrderReturnDto dto) throws Exception;

    List<OrderDto> getHistory(String username) throws Exception;
    
    OrderDto deliverOrder(Long id) throws Exception;
}
