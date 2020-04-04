package vn.com.fobelife.component.cart.preparer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.model.HistoryRepModel;
import vn.com.fobelife.component.cart.service.CartService;

@Controller
@Slf4j
public class HistoryPreparer extends AbstractViewPreparer {

    @Autowired
    private CartService cartService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            HistoryRepModel  model = getModel(req, HistoryRepModel.NAME, HistoryRepModel.class);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            List<OrderDto> data = cartService.getHistory(username);
            req.setAttribute("currentPage", "history");
            model.setAdmin(username.startsWith("admin"));
            model.setData(data);
        } catch (Exception e) {
            log.error("***** HistoryPreparer: ", e);
        }
    }

    
}
