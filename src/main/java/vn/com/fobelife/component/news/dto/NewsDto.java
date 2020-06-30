package vn.com.fobelife.component.news.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDto {

    private String subject;
    private List<String> contentList;
}
