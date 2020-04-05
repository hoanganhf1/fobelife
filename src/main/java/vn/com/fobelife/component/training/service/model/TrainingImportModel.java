package vn.com.fobelife.component.training.service.model;

import lombok.Getter;

@Getter
public class TrainingImportModel {

    private String questionCode;
    private String optionCode;
    private String content;
    private String anwser;
    private String status;

    public TrainingImportModel(String[] properties) {
        this.questionCode = properties[0];
        this.optionCode = properties[1];
        this.content = properties[2];
        this.anwser = properties[3];
        this.status = properties[4];
    }
}
