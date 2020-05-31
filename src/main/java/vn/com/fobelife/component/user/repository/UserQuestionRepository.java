package vn.com.fobelife.component.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.training.entity.Question;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.entity.UserQuestion;

public interface UserQuestionRepository extends CrudRepository<UserQuestion, Long> {

    Optional<UserQuestion> findByUserAndQuestion(User user, Question question);

    List<UserQuestion> findByUserAndPassedAndQuestionIn(User user, Boolean passed, List<Question> questions);
}
