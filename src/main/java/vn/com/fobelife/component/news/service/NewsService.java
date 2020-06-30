package vn.com.fobelife.component.news.service;

import vn.com.fobelife.component.news.dto.NewsDto;

public interface NewsService {

    void importNews(String content) throws Exception;

    NewsDto getByCode(String code) throws Exception;
}
