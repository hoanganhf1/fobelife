package vn.com.fobelife.component.product.controller;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.product.controller.model.ProductForm;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.service.ProductService;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String getProduct(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** PRODUCT MANAGEMENT PAGE *****");
        return "product";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute ProductForm model, HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** Create new product *****");
        try {
            ProductDto dto = new ProductDto();
            dto.setName(model.getName());
            dto.setDescription(model.getDescription());
            dto.setImage(model.getImage());
            dto.setPrice(model.getPrice());
            dto.setStatus(model.getStatus());
            dto.setStatus("ACTIVE");
            service.save(dto);
        } catch (Exception e) {
            log.error("***** Create new product: ", e);
        }
        return "product";
    }

    @PostMapping("/import")
    public String importProduct(@RequestParam MultipartFile file) {
        log.info("***** IMPORT PRODUCT *****");
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            service.importProduct(content);
        } catch (Exception e) {
            log.error("***** Import products", e);
        }
        return "product";
    }
}
