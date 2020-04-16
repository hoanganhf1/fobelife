package vn.com.fobelife.component.training.controller.preparer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.common.tiles.AbstractViewPreparer;
import vn.com.fobelife.component.training.controller.model.TrainingModel;
import vn.com.fobelife.component.training.dto.QuestionDto;
import vn.com.fobelife.component.training.service.TrainingService;

@Controller
@Slf4j
public class TrainingPreparer extends AbstractViewPreparer {

    @Autowired
    private TrainingService trainingService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        try {
            HttpServletRequest req = getServletRequest(tilesContext);
            TrainingModel model = getModel(req, TrainingModel.NAME, TrainingModel.class);
            List<QuestionDto> data = trainingService.getByCurrentUser(String.valueOf(req.getAttribute("courseCode")));
            req.setAttribute("currentPage", "training");
            model.setData(data);
            model.setNumberOfPassed(trainingService.countResult(Boolean.TRUE));
            model.setNumberOfFailed(trainingService.countResult(Boolean.FALSE));
            model.setNumberOfAvailable(data.size() - model.getNumberOfPassed() - model.getNumberOfFailed());
        } catch (Exception e) {
            log.error("***** TrainingPreparer: ", e);
        }
    }

}
