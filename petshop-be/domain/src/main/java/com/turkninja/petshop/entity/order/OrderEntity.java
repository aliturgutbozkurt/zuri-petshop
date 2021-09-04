package com.turkninja.petshop.entity.order;

import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "ORDER")
@Data
public class OrderEntity extends BaseEntity {

    @Column(name = "NUMBER")
    private int number;

    @JoinColumn(name = "STATE", nullable = false)
    private OrderState state;

    @JoinColumn(name = "PRICE", nullable = false)
    private BigDecimal price;

    @JoinColumn(name = "PAYMENT_METHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @JoinColumn(name = "PREFERRED_DELIVERY_DATE")
    private Date preferredDeliveryDate;

    @JoinColumn(name = "PREFERRED_DELIVERY_TIME_START")
    private Time preferredDeliveryTimeStart;

    @JoinColumn(name = "PREFERRED_DELIVERY_TIME_END")
    private Time preferredDeliveryTimeEnd;

    @JoinColumn(name = "DEALER_NOTE")
    private String dealerNote;

    @JoinColumn(name = "CUSTOMER_NOTE")
    private String customerNote;

    @JoinColumn(name = "EXPLANATION")
    private String explanation;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderItemEntity> orderItems;
}