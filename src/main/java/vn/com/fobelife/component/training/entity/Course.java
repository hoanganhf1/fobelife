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

@Getter
@Setter
@Table(name = "COURSE", indexes = { @Index(columnList = "STATUS", unique = false),
        @Index(columnList = "CODE", unique = true)})
@Entity
public class Course extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2391976540314458670L;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "course")
    Collection<Question> questions;
}
