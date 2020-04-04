package vn.com.fobelife.component.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

/**
 * 
 * @author ahuynh
 *
 */
@Entity
@Table(name = "USERS", indexes = {@Index(columnList = "EMAIL", unique = true)})
@Getter
@Setter
public class User extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -1382255891151247690L;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    @Column(name = "TRAINING_POINT")
    private Integer point;

    @Column(name = "ACTIVE")
    private Boolean active;
}
