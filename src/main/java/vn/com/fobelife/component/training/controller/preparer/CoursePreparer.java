package vn.com.fobelife.component.training.controller.preparer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.training.controller.model.CourseModel;
import vn.com.fobelife.component.training.dto.CourseDto;
import vn.com.fobelife.component.training.service.TrainingService;

@Controller
@Slf4j
public class CoursePreparer extends AbstractViewPreparer {

    @Autowired
    private TrainingService trainingService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            CourseModel model = getModel(req, CourseModel.NAME, CourseModel.class);
            List<CourseDto> data = trainingService.getCourses();
            req.setAttribute("currentPage", "training");
            model.setData(data);
        } catch (Exception e) {
            log.error("***** CoursePreparer: ", e);
        }
    }

}
