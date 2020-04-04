package vn.com.fobelife.component.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @GetMapping
    public String getHome(HttpServletRequest req, HttpServletResponse rep) {
        log.info("********* HOME PAGE *****");
        return "redirect:/cart/fobelife";
    }

}
