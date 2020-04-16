package vn.com.fobelife.component.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/management")
@Slf4j
public class ManageController {

    @GetMapping
    public String getNews(HttpServletRequest req, HttpServletResponse rep) {
        log.info("********* MANAGER PAGE *****");
        return "management";
    }
}
