package com.turkninja.petshop.entity.order;

import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;

import com.turkninja.petshop.sequence.OrderNumberGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(generator = OrderNumberGenerator.NAME)
    @GenericGenerator(name = OrderNumberGenerator.NAME, strategy = OrderNumberGenerator.STRATEGY)
    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "STATE", nullable = false)
    private OrderState state;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column(name = "DELIVERY_DATE")
    private LocalDate deliveryDate;

    @Column(name = "DELIVERY_TIME_START")
    private LocalTime deliveryTimeStart;

    @Column(name = "DELIVERY_TIME_END")
    private LocalTime deliveryTimeEnd;

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