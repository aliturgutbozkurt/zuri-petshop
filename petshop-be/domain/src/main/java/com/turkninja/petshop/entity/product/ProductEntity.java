package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Data
@Builder
public class ProductEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "oldPrice",nullable = false)
    private BigDecimal oldPrice = BigDecimal.ZERO;

    @Column(name = "price",nullable = false)
    private BigDecimal price = BigDecimal.ONE;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProductImageEntity> images = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

    public void setProductToImage(){
        for(ProductImageEntity imageEntity:getImages()){
            imageEntity.setProduct(this);
        }
    }

    public void addImage(ProductImageEntity imageEntity){
        getImages().add(imageEntity);
    }
}
