package vn.com.fobelife.component.gift.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/gift")
@Slf4j
public class GiftController {

    @GetMapping
    public String getGift(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** GIFT PAGE *****");
        req.setAttribute("type", "gift");
        return "gift";
    }
}
