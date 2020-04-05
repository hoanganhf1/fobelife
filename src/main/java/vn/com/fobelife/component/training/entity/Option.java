package vn.com.fobelife.component.training.entity;

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

@Entity
@Table(name = "OPTIONS", indexes = { @Index(columnList = "CODE", unique = true) })
@Getter
@Setter
public class Option extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4804490066954386797L;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ORDINAL")
    private Integer ordinal;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}
