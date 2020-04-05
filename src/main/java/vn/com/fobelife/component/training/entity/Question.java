package vn.com.fobelife.component.training.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

@Entity
@Table(name = "QUESTIONS",
       indexes = { @Index(columnList = "CODE", unique = true)})
@Getter
@Setter
public class Question extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -6885990287837384164L;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORDINAL")
    private Integer ordinal;

    @OneToMany(mappedBy = "question")
    private Collection<Option> options;
}
