package vn.com.fobelife.component.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.training.entity.Course;
import vn.com.fobelife.component.training.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Optional<Question> findByCode(String code);
    List<Question> findByStatus(String status);
    List<Question> findByStatusAndCourse(String status, Course course);
}
