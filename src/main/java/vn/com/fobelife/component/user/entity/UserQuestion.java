package vn.com.fobelife.component.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.component.training.entity.Question;

@Entity
@Table(name = "USER_QUESTIONS", indexes = { @Index(columnList = "USER_ID, QUESTION_ID", unique = true) })
@Getter
@Setter
public class UserQuestion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3331506697014214608L;

    @EmbeddedId
    private UserQuestionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("questionId")
    private Question role;

    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Embeddable
    @Getter
    @Setter
    public static class UserQuestionId implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = -9209345006380383951L;

        @JoinColumn(name = "USER_ID", nullable = false)
        private Long userId;

        @JoinColumn(name = "QUESTION_ID", nullable = false)
        private Long questionId;

        protected UserQuestionId() {

        }

        public UserQuestionId(Long userId, Long questionId) {
            this.userId = userId;
            this.questionId = questionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            UserQuestionId that = (UserQuestionId) o;
            return Objects.equals(this.userId, that.userId) && Objects.equals(this.questionId, that.questionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.userId, this.questionId);
        }
    }
}
