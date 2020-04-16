package vn.com.fobelife.component.training.service.model;

import lombok.Getter;

@Getter
public class CourseImportModel {

    private String code;
    private String name;
    private String status;

    public CourseImportModel(String[] properties) {
        this.code = properties[0];
        this.name = properties[1];
        this.status = properties[2];
    }
}
