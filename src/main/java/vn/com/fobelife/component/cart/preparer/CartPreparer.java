/**
 * 
 */
package vn.com.fobelife.component.cart.preparer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.cart.dto.OrderItemDto;
import vn.com.fobelife.component.product.controller.model.ProductModel;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.service.ProductService;
import vn.com.fobelife.component.user.service.UserService;

/**
 * @author ahuynh
 *
 */
@Controller
@Slf4j
public class CartPreparer extends AbstractViewPreparer {

    @Autowired
    private ProductService proService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            ProductModel model = getModel(req, ProductModel.NAME, ProductModel.class);
            String type = String.valueOf(req.getAttribute("type"));
            List<ProductDto> data = new ArrayList<>();
            if ("review".equalsIgnoreCase(type)) {
                Object oItems = req.getAttribute("items");
                if (oItems != null && oItems instanceof List) {
                    List<?> items = (List<?>)oItems;
                    for (Object i : items) {
                        OrderItemDto oI = (OrderItemDto)i;
                        ProductDto p = proService.getByCode(oI.getProductCode());
                        p.setQuantity(oI.getQuantity());
                        p.setTotal(oI.getTotal());
                        data.add(p);
                    }
                }
                model.setTotal(String.valueOf(req.getAttribute("orderTotal")));
            } else {
                data = proService.getByStatusAndType("ACTIVE", type);
            }
            model.setPageType(type);
            model.setData(data);
            model.setPoint(userService.getCurrent().getPoint());
            model.setPointUsed(NumberUtils.toInt(String.valueOf(req.getAttribute("point"))));
            req.setAttribute("currentPage", type);
        } catch (Exception e) {
            log.error("***** ProductPreparer:", e);
        }
    }

}
