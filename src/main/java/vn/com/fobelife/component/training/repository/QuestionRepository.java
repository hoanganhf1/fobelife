package vn.com.fobelife.component.training.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.training.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Optional<Question> findByCode(String code);
}
