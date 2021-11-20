package com.turkninja.petshop.entity.sales;

import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SALES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesEntity extends BaseEntity {
    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "IS_PAID")
    private Boolean isPaid;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}