package vn.com.fobelife.component.training.service;

import java.util.List;

import vn.com.fobelife.component.training.dto.QuestionDto;

public interface TrainingService {

    void importTraining(String csvContent) throws Exception;

    List<QuestionDto> getByCurrentUser() throws Exception;

    List<String> getQuestionCode() throws Exception;

    void submitTraining(List<String> answerCodes) throws Exception;

    Integer countResult(Boolean result) throws Exception;
}
