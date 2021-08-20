package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Entity
@Table(name = "PRODUCT_IMAGE")
@EqualsAndHashCode(of = {"id", "url", "orderNumber"})
@Data
public class ProductImageEntity extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
