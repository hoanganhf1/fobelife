/**
 * 
 */
package vn.com.fobelife.component.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ahuynh
 *
 */
@Entity
@Table(name = "ROLES", indexes = {@Index(columnList = "NAME", unique = true)})
@Getter
@Setter
public class Role implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7319727133961195478L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
