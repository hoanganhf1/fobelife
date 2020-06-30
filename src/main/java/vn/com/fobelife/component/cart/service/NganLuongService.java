package vn.com.fobelife.component.cart.service;

import vn.com.fobelife.component.cart.dto.OrderDto;

public interface NganLuongService {

    String checkoutVisa(OrderDto order) throws Exception;
    String checkoutInstallment(OrderDto order) throws Exception;
    String decrypt(String encryptText) throws Exception;
}
