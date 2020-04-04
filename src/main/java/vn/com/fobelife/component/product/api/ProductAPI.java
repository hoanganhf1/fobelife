/**
 * 
 */
package vn.com.fobelife.component.product.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.api.ResultInfo;
import vn.com.fobelife.component.product.api.model.ProductModel;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.service.ProductService;

/**
 * @author ahuynh
 *
 */
@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductAPI {

    @Autowired
    private ProductService service;

    @PutMapping
    public ResponseEntity<ResultInfo> updateStatus(@RequestBody ProductModel model) {
        ProductDto data = new ProductDto();
        String status = "SUCCESS";
        String message = "Updated status.";
        try {
            data = service.updateStatus(model.getCode(), model.getStatus());
        } catch (Exception e) {
            status = "FAILED";
            message = e.getMessage();
            log.error("******** Update product status", e);
        }
        return new ResponseEntity<>(ResultInfo.builder().status(status).message(message).data(data).build(), HttpStatus.OK);
    }
}
