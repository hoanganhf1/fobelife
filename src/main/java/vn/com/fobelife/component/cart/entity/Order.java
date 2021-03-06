package vn.com.fobelife.component.cart.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.com.fobelife.common.entity.BaseEntity;

@Entity
@Table(name = "ORDERS", indexes = { @Index(columnList = "USERNAME", unique = false),
        @Index(columnList = "CODE", unique = true), @Index(columnList = "TRANSACTION_INFO", unique = true) })
@Getter
@Setter
public class Order extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1122273152868614417L;

    @Column(name = "CODE")
    private String code;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TOTAL")
    private String total;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TRANSACTION_INFO")
    private String transactionInfo;

    @Column(name = "PAYMENT_ID")
    private Integer paymentId;

    @Column(name = "PAYMENT_TYPE")
    private Integer paymentType;

    @Column(name = "ERROR_TEXT")
    private String errorText;

    @Column(name = "SECURE_CODE")
    private String secureCode;

    @Column(name = "TOKEN_NL")
    private String tokenNl;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "NOTE", length = 2000)
    private String note;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public void setPriceIfNotNull(Integer price) {
        if (price != null)
            this.price = price;
    }

    public void setPaymentIdIfNotNull(Integer paymentId) {
        if (paymentId != null) 
            this.paymentId = paymentId;
    }

    public void setPaymentTypeIfNotNull(Integer paymentType) {
        if (paymentType != null) 
            this.paymentType = paymentType;
    }

    public void setErrorTextIfNotNull(String errorText) {
        if (errorText != null)
            this.errorText = errorText;
    }

    public void setSecureCodeIfNotNull(String secureCode) {
        if (secureCode != null)
            this.secureCode = secureCode;
    }

    public void setTokenNlIfNotNull(String tokenNl) {
        if (tokenNl != null)
            this.tokenNl = tokenNl;
    }
}
