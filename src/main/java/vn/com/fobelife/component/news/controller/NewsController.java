package vn.com.fobelife.component.news.controller;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.news.service.NewsService;

@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping("/{code}")
    public String getNews(HttpServletRequest req, HttpServletResponse rep, @PathVariable String code) {
        log.info("********* NEWS PAGE *****");
        req.setAttribute("code", code);
        return "news";
    }

    @PostMapping("/import")
    public String importProduct(@RequestParam MultipartFile file) {
        log.info("***** IMPORT PRODUCT *****");
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            service.importNews(content);
        } catch (Exception e) {
            log.error("***** Import products", e);
        }
        return "redirect:/cart/fobelife";
    }
}
