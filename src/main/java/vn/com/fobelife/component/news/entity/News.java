/**
 * 
 */
package vn.com.fobelife.component.news.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

/**
 * @author ahuynh
 *
 */
@Getter
@Setter
@Entity
@Table(name = "NEWS",
        indexes = { @Index(columnList = "CODE", unique = true)})
public class News extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -4445258255383678990L;

    @Column(name = "CODE")
    private String code;
    
    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "news")
    List<NewsContent> contentList;
}
