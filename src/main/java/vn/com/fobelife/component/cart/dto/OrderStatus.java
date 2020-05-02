package vn.com.fobelife.component.cart.dto;

import lombok.Getter;

public enum OrderStatus {

    ORDERED("Đặt hàng"),
    DELIVERED("Giao hàng"),
    PAID("Thanh toán"),
    CANCEL("Hủy");

    @Getter
    private String description;

    private OrderStatus(String description) {
        this.description = description;
    }
}
