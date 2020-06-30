/**
 * 
 */
package vn.com.fobelife.component.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NEWS_CONTENT")
public class NewsContent extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -4445258255383678990L;

    @ManyToOne
    @JoinColumn(name = "NEWS_ID")
    private News news;

    @Column(name = "CONTENT")
    private String content;
}
