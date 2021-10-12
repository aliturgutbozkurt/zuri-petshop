package com.turkninja.petshop.entity.order;

import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
public class OrderEntity extends BaseEntity {

    // TODO: Auto incremented order number will be added!

    @Column(name = "STATE", nullable = false)
    private OrderState state;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

    @Column(name = "DELIVERY_TIME_START")
    private Time deliveryTimeStart;

    @Column(name = "DELIVERY_TIME_END")
    private Time deliveryTimeEnd;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "PROMOTION_CODE")
    private String promotionCode;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ADDRESS_ID")
    private UserAddressEntity userAddress;
}