package vn.com.fobelife.component.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

@Entity
@Table(name = "ORDER_ITEMS")
@Getter
@Setter
public class OrderItem extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1122273152868614417L;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL")
    private Integer total;

}
