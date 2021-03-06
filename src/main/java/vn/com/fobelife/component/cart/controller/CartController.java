/**
 * 
 */
package vn.com.fobelife.component.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.dto.OrderItemDto;
import vn.com.fobelife.component.cart.dto.OrderStatus;
import vn.com.fobelife.component.cart.model.CheckoutForm;
import vn.com.fobelife.component.cart.service.CartService;
import vn.com.fobelife.component.cart.service.NganLuongService;

/**
 * @author ahuynh
 *
 */
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private NganLuongService nganluong;

    @GetMapping
    public String getCart(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** Cart {} *****");
        return "redirect:/cart/fobelife";
    }

    @GetMapping("/{type}")
    public String getCartType(@PathVariable String type, HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** Cart {} *****", type);
        req.setAttribute("type", type);
        return "cart";
    }

    @GetMapping("/history")
    public String getHistory(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** History *****");
        return "history";
    }

    @PostMapping
    public String checkout(@ModelAttribute CheckoutForm model, 
            HttpServletRequest req, HttpServletResponse rep,
            HttpSession session) {
        log.info("***** Check out *****");
        String redirectUrl = "/cart/history";
        if (StringUtils.isNotBlank(model.getPaymentType())) {
            try {
                OrderDto dto = new OrderDto();
                dto.setTotal(model.getCartTotal());
                dto.setItems(getItems(model));
                dto.setType(model.getPaymentType());
                dto.setStatus(OrderStatus.ORDERED);
                dto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
                dto.setNote(model.getNote());
                dto.setPoint(model.getPointUsed());
                dto = cartService.createOrder(dto);
                if ("visa".equalsIgnoreCase(model.getPaymentType())) {
                    redirectUrl = nganluong.checkoutVisa(dto);
                } else if ("VISA-INSTALLMENT".equalsIgnoreCase(model.getPaymentType())) {
                    session.setAttribute("orderCode", dto.getCode());
                    redirectUrl = nganluong.checkoutInstallment(dto);
                }
            } catch (Exception e) {
                log.error("***** save cart: ", e);
            }
        } else {
            req.setAttribute("point", model.getPointUsed());
            req.setAttribute("type", "review");
            req.setAttribute("items", getItems(model));
            req.setAttribute("orderTotal", model.getCartTotal());
            return "cart";
        }
        log.debug("redirectURL: {}",redirectUrl);
        return "redirect:" + redirectUrl;
    }

    private List<OrderItemDto> getItems(CheckoutForm model) {
        List<OrderItemDto> itemDtos = new ArrayList<>();
        for (String pO : model.getProductOrder()) {
            String[] product = pO.split(" ");
            if (!"0".equalsIgnoreCase(product[1])) {
                OrderItemDto item = new OrderItemDto();
                item.setProductCode(product[0]);
                item.setQuantity(Integer.valueOf(product[1]));
                item.setTotal(product[2]);
                itemDtos.add(item);
            }
        }
        return itemDtos;
    }
}
