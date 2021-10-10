package com.turkninja.petshop.entity.order;

import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Data
public class OrderItemEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @Column(name = "COUNT", nullable = false)
    private Integer count;
}