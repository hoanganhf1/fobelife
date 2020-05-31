package vn.com.fobelife.component.training.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;

import vn.com.fobelife.component.training.dto.CourseDto;
import vn.com.fobelife.component.training.dto.OptionDto;
import vn.com.fobelife.component.training.dto.QuestionDto;
import vn.com.fobelife.component.training.entity.Course;
import vn.com.fobelife.component.training.entity.Option;
import vn.com.fobelife.component.training.entity.Question;
import vn.com.fobelife.component.training.repository.CourseRepository;
import vn.com.fobelife.component.training.repository.OptionRepository;
import vn.com.fobelife.component.training.repository.QuestionRepository;
import vn.com.fobelife.component.training.service.TrainingService;
import vn.com.fobelife.component.training.service.model.CourseImportModel;
import vn.com.fobelife.component.training.service.model.TrainingImportModel;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.entity.UserQuestion;
import vn.com.fobelife.component.user.repository.UserQuestionRepository;
import vn.com.fobelife.component.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private QuestionRepository qRepo;

    @Autowired
    private OptionRepository oRepo;

    @Autowired
    private UserQuestionRepository uqRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Override
    @Transactional(readOnly = false)
    public void importTraining(String csvContent) throws Exception {
        CSVReader reader = new CSVReader(new StringReader(csvContent));
        String[] line;
        int lineNumber = 1;
        Course course = null;
        Question q = null;
        while ((line = reader.readNext()) != null) {
            if (lineNumber == 1 || lineNumber == 3) {
                lineNumber++;
                continue;
            }
            if (lineNumber == 2) {
                CourseImportModel model = new CourseImportModel(line);
                course = courseRepo.findByCode(model.getCode()).orElse(new Course());
                course.setName(model.getName());
                course.setStatus(model.getStatus());
                course.setCode(model.getCode());
                course = courseRepo.save(course);
                lineNumber++;
                continue;
            }
            TrainingImportModel model = new TrainingImportModel(line);
            if (StringUtils.isNotBlank(model.getQuestionCode())) {
                q = qRepo.findByCode(model.getQuestionCode()).orElse(new Question());
                q.setCode(model.getQuestionCode());
                q.setContent(model.getContent());
                q.setAnswer(model.getAnwser());
                q.setStatus(model.getStatus());
                q.setCourse(course);
                q = qRepo.save(q);
            } else {
                String oCode = q.getCode() + "-" + model.getOptionCode();
                Option o = oRepo.findByCode(oCode);
                if (o == null) {
                    o = new Option();
                    o.setCode(oCode);
                    o.setContent(model.getContent());
                    o.setQuestion(q);
                    o = oRepo.save(o);
                }
            }
        }
        reader.close();
    }

    @Override
    public List<QuestionDto> getByCurrentUser(String courseCode) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username);
        Course course = courseRepo.findByCode(courseCode).get();
        Iterable<Question> questions = qRepo.findByStatusAndCourse("ACTIVE", course);
        List<QuestionDto> dtos = new ArrayList<>();
        questions.forEach(q -> {
            UserQuestion uq = uqRepo.findByUserAndQuestion(user, q).orElse(new UserQuestion());
            QuestionDto dto = applyDto(q);
            dto.setUserAnswer(uq.getAnswer());
            dto.setPassed(uq.getPassed());
            dtos.add(dto);
        });
        return dtos;
    }

    private QuestionDto applyDto(Question entity) {
        QuestionDto dto = new QuestionDto();
        dto.setCode(entity.getCode());
        dto.setContent(entity.getContent());
        dto.setAnswer(entity.getCode() + "-" + entity.getAnswer());
        dto.setOptions(applyOptionDto(entity.getOptions()));
        return dto;
    }

    private List<OptionDto> applyOptionDto(Collection<Option> options) {
        List<OptionDto> dtos = new ArrayList<>();
        options.forEach(o -> {
            OptionDto dto = new OptionDto();
            dto.setCode(o.getCode());
            dto.setContent(o.getContent());
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public List<String> getQuestionCode() throws Exception {
        List<String> codes = new ArrayList<>();
        Iterable<Question> questions = qRepo.findAll();
        questions.forEach(q -> codes.add(q.getCode()));
        return codes;
    }

    @Override
    @Transactional(readOnly = false)
    public void submitTraining(List<String> answerCodes) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username);
        for (String a : answerCodes) {
            String qCode = a.split("-")[0];
            Question question = qRepo.findByCode(qCode).get();
            UserQuestion uq = uqRepo.findByUserAndQuestion(user, question).orElse(new UserQuestion(user, question));
            uq.setPassed(a.equalsIgnoreCase(question.getCode() + "-" + question.getAnswer()));
            uq.setAnswer(a);
            uq = uqRepo.save(uq);
            if (a.equalsIgnoreCase(question.getCode() + "-" + question.getAnswer())) {
                user.setPoint(user.getPoint() + 1);
                user = userRepo.save(user);
            }
        }
    }

    @Override
    public Integer countResult(String courseCode, Boolean result) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Course course = courseRepo.findByCode(courseCode).get();
        List<Question> questions = qRepo.findByStatusAndCourse("ACTIVE", course);
        User user = userRepo.findByUsername(username);
        List<UserQuestion> uqList = uqRepo.findByUserAndPassedAndQuestionIn(user, result, questions);
        return uqList.size();
    }

    @Override
    public List<CourseDto> getCourses() throws Exception {
        List<CourseDto> dtos = new ArrayList<>();
        List<Course> courses = courseRepo.findByStatus("ACTIVE");
        courses.stream().forEach(c -> {
            CourseDto dto = new CourseDto();
            dto.setCode(c.getCode());
            dto.setName(c.getName());
            dtos.add(dto);
        });
        return dtos;
    }

}
