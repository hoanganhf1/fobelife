package vn.com.fobelife.component.news.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.news.dto.NewsDto;
import vn.com.fobelife.component.news.entity.News;
import vn.com.fobelife.component.news.entity.NewsContent;
import vn.com.fobelife.component.news.repository.NewsContentRepository;
import vn.com.fobelife.component.news.repository.NewsRepository;
import vn.com.fobelife.component.news.service.NewsService;

@Service
@Slf4j
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepo;

    @Autowired
    private NewsContentRepository newsContentRepo;

    @Override
    public void importNews(String csvContent) throws Exception {
        log.info("****** IMPORT NEWS *****");
        CSVReader reader = new CSVReader(new StringReader(csvContent));
        String[] line;
        boolean isFirstLine = true;
        News news = null;
        while ((line = reader.readNext()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            NewsImportModel model = new NewsImportModel(line);
            if (StringUtils.isNotBlank(model.getCode())) {
                news = newsRepo.findByCode(model.getCode()).orElse(new News());
                news.setCode(model.getCode());
                news.setSubject(model.getContent());
                news = newsRepo.save(news);
                if (news.getContentList() != null && !news.getContentList().isEmpty()) {
                    newsContentRepo.deleteAll(news.getContentList());
                }
            } else {
                NewsContent content = new NewsContent();
                content.setContent(model.getContent());
                content.setNews(news);
                content = newsContentRepo.save(content);
            }
        }
        reader.close();
    }

    @Getter
    protected class NewsImportModel {
        private String code;
        private String content;

        public NewsImportModel(String[] line) {
            this.code = line[0];
            this.content = line[1];
        }
    }

    @Override
    public NewsDto getByCode(String code) throws Exception {
        News news = newsRepo.findByCode(code).get();
        NewsDto dto = new NewsDto();
        dto.setSubject(news.getSubject());
        List<String> contentList = new ArrayList<>();
        if (news.getContentList() != null && !news.getContentList().isEmpty()) {
            news.getContentList().forEach(c -> contentList.add(c.getContent()));
        }
        dto.setContentList(contentList);
        return dto;
    }
}
