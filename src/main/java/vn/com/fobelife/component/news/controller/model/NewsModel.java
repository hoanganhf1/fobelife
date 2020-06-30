package vn.com.fobelife.component.news.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsModel {

    public static final String NAME = "mNews";

    private String subject;
    private List<String> contentList;
}
