package vn.com.fobelife.component.training.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.training.dto.QuestionDto;

@Getter
@Setter
public class TrainingModel {

    public static final String NAME = "mTraining";

    private List<QuestionDto> data;

    private Integer numberOfPassed;

    private Integer numberOfFailed;

    private Integer numberOfAvailable;
}
