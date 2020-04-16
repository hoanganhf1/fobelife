package vn.com.fobelife.component.training.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.training.dto.CourseDto;

@Getter
@Setter
public class CourseModel {

    public static final String NAME = "mCourse";

    private List<CourseDto> data;

}
