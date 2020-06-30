package vn.com.fobelife.component.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.cart.dto.OrderReturnDto;
import vn.com.fobelife.component.cart.service.CartService;

@Controller
@RequestMapping("/nganluong")
@Slf4j
public class NganLuongController {

    @Autowired
    private CartService cartService;



    @GetMapping("/return")
    public String returnFromNganLuong(HttpServletRequest req, HttpServletResponse rep, 
            HttpSession session,
            @Parameter String transaction_info,
            @Parameter Integer price,
            @Parameter Integer payment_id,
            @Parameter Integer payment_type,
            @Parameter String error_text,
            @Parameter String secure_code,
            @Parameter String token_nl,
            @Parameter String order_code,
            @Parameter String data) {
        log.info("Return from Ngan Luong");
        try {

            OrderReturnDto dto = OrderReturnDto.builder()
                    .transactionInfo(transaction_info)
                    .price(price)
                    .paymentId(payment_id)
                    .paymentType(payment_type)
                    .errorText(error_text)
                    .secureCode(secure_code)
                    .tokenNl(token_nl)
                    .orderCode(order_code)
                    .build();
            if (StringUtils.isNotBlank(data)) {
                String orderCode = String.valueOf(session.getAttribute("orderCode"));
                dto.setOrderCode(orderCode);
            }
            cartService.updateOrder(dto);
        } catch(Exception e) {
            log.error("Ngan luong return: ", e);
        }
        return "redirect:/cart/history";
    }

    @GetMapping("/cancel")
    public String cancelFromNganLuong(HttpServletRequest req, HttpServletResponse rep) {
        log.info("Return from Ngan Luong");
        
        return "redirect:/cart/history";
    }
}
