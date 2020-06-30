package vn.com.fobelife.component.news.repository;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.news.entity.NewsContent;

public interface NewsContentRepository extends CrudRepository<NewsContent, Long> {

}
