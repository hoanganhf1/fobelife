package vn.com.fobelife.component.training.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {


    private String code;

    private String content;

    private String answer;

    private String userAnswer;

    private Boolean passed;

    private List<OptionDto> options;
}
