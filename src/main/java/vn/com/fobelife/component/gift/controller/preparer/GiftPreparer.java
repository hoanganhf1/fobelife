package vn.com.fobelife.component.gift.controller.preparer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.gift.controller.model.GiftModel;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.service.ProductService;
import vn.com.fobelife.component.user.service.UserService;

@Controller
@Slf4j
public class GiftPreparer extends AbstractViewPreparer {

    @Autowired
    private ProductService proService;
    
    @Autowired
    private UserService userService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            GiftModel model = getModel(req, GiftModel.NAME, GiftModel.class);
            String type = String.valueOf(req.getAttribute("type"));
            List<ProductDto> data = proService.getByStatusAndType("ACTIVE", type);
            model.setPageType(type);
            model.setData(data);
            model.setPoint(userService.getCurrent().getPoint());
            req.setAttribute("currentPage", "gift");
        } catch (Exception e) {
            log.error("***** GiftPreparer:", e);
        }
    }
}
