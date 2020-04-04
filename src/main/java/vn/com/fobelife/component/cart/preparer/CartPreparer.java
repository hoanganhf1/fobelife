/**
 * 
 */
package vn.com.fobelife.component.cart.preparer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.product.controller.model.ProductModel;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.service.ProductService;

/**
 * @author ahuynh
 *
 */
@Controller
@Slf4j
public class CartPreparer extends AbstractViewPreparer {

    @Autowired
    private ProductService proService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            ProductModel model = getModel(req, ProductModel.NAME, ProductModel.class);
            String type = String.valueOf(req.getAttribute("type"));
            List<ProductDto> data = proService.getByStatusAndType("ACTIVE", type);
            model.setPageType(type);
            model.setData(data);
            req.setAttribute("currentPage", type);
        } catch (Exception e) {
            log.error("***** ProductPreparer:", e);
        }
    }

}
