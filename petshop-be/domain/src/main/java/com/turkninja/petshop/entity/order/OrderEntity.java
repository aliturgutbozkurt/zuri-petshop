package com.turkninja.petshop.entity.order;

import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "ORDER")
@Data
public class OrderEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMBER", unique = true, nullable = false, insertable = false, updatable = false)
    private Integer number;

    @Column(name = "STATE", nullable = false)
    private OrderState state;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "DELIVERY_DATE", nullable = false)
    private Date deliveryDate;

    @Column(name = "DELIVERY_TIME_START", nullable = false)
    private Time deliveryTimeStart;

    @Column(name = "DELIVERY_TIME_END", nullable = false)
    private Time deliveryTimeEnd;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "PROMOTION_CODE")
    private String promotionCode;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ADDRESS_ID")
    private UserAddressEntity userAddress;
}