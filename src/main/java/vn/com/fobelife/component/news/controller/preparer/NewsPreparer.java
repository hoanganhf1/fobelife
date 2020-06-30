package vn.com.fobelife.component.news.controller.preparer;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.news.controller.model.NewsModel;
import vn.com.fobelife.component.news.dto.NewsDto;
import vn.com.fobelife.component.news.service.NewsService;

@Controller
@Slf4j
public class NewsPreparer extends AbstractViewPreparer {

    @Autowired
    private NewsService newsService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            req.setAttribute("currentPage", "news");
            NewsModel model = getModel(req, NewsModel.NAME, NewsModel.class);

            String code = req.getAttribute("code") != null ? req.getAttribute("code").toString() : null;
            NewsDto data = newsService.getByCode(code);
            if (data != null) {
                model.setSubject(data.getSubject());
                model.setContentList(data.getContentList());
            }
        
        } catch (Exception e) {
            log.error("***** News preparer: ", e);
        }
    }

}
