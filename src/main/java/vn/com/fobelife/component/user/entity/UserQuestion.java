package vn.com.fobelife.component.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;
import vn.com.fobelife.component.training.entity.Question;

@Entity
@Table(name = "USER_QUESTIONS", indexes = { @Index(columnList = "USER_ID, QUESTION_ID", unique = true) })
@Getter
@Setter
public class UserQuestion extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 3331506697014214608L;

    public UserQuestion() {
        // TODO Auto-generated constructor stub
    }

    public UserQuestion(User user, Question question) {
        this.user = user;
        this.question = question;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "PASSED")
    private Boolean passed;
}
