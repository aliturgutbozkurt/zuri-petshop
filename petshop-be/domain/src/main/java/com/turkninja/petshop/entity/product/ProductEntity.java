package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Entity
@Table(name = "PRODUCT")
@Data
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;


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
