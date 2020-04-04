/**
 * 
 */
package vn.com.fobelife.component.user.controller.preparer;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Controller;

import vn.com.fobelife.common.tiles.AbstractViewPreparer;

/**
 * @author ahuynh
 *
 */
@Controller
public class UserSignupPreparer extends AbstractViewPreparer {

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        HttpServletRequest req = getServletRequest(tilesContext);
        req.setAttribute("currentPage", "signup");
    }

}
