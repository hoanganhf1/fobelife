/**
 * 
 */
package vn.com.fobelife.component.product.controller.preparer;

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
public class ProductPreparer extends AbstractViewPreparer {

    @Autowired
    private ProductService proService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            ProductModel model = getModel(req, ProductModel.NAME, ProductModel.class);
            List<ProductDto> data = proService.getAll();
            model.setData(data);
            req.setAttribute("currentPage", "product");
        } catch (Exception e) {
            log.error("***** ProductPreparer:", e);
        }
    }

}
