package vn.com.fobelife.component.news.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.news.entity.News;

public interface NewsRepository extends CrudRepository<News, Long> {

    Optional<News> findByCode(String code);
}
