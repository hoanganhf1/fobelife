/**
 * 
 */
package vn.com.fobelife.component.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

/**
 * @author ahuynh
 *
 */
@Entity
@Table(name = "PRODUCTS", 
       indexes = { @Index(columnList = "CODE", unique = true),
                   @Index(columnList = "STATUS", unique = false),
                   @Index(columnList = "TYPE", unique = false)})
@Getter
@Setter
public class Product extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 6703167132229279508L;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "IMAGE", length = 2000)
    private String image;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STEP")
    private Integer step;
}
