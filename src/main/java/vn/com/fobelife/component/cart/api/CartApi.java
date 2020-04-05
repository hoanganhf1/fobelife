/**
 * 
 */
package vn.com.fobelife.component.cart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.api.ResultInfo;
import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.service.CartService;

/**
 * @author ahuynh
 *
 */
@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartApi {

    @Autowired
    private CartService service;

    @PutMapping("/deliver/{id}")
    public ResponseEntity<ResultInfo> deliverOrder(@PathVariable Long id) {
        OrderDto data = null;
        String status = "SUCCESS";
        String message = "Updated status.";
        try {
            data = service.deliverOrder(id);
        } catch (Exception e) {
            status = "FAILED";
            message = e.getMessage();
            log.error("******** Update product status", e);
        }
        return new ResponseEntity<>(ResultInfo.builder().status(status).message(message).data(data).build(),
                HttpStatus.OK);
    }

    @PutMapping("/gift/{productCode}")
    public ResponseEntity<ResultInfo> submitGift(@PathVariable String productCode) {
        HttpStatus code = HttpStatus.OK;
        OrderDto dto = null;
        String status = "SUCCESS";
        String message = "Phần quà đã được ghị nhận, chúng tôi sẽ sớm chuyển đến bạn.";
        try {
            dto = service.submitGift(productCode);
        } catch (Exception e) {
            status = "FAILED";
            message = e.getMessage();
            code = HttpStatus.NOT_FOUND;
            log.error("******** Update product status", e);
        }
        return new ResponseEntity<>(ResultInfo.builder().status(status).message(message).data(dto).build(),
                code);
    }
}
