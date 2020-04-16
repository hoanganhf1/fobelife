package vn.com.fobelife.component.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.training.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    List<Course> findByStatus(String status);
}
