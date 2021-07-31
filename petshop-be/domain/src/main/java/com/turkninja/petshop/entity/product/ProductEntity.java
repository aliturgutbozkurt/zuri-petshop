package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Entity
@Table(name = "PRODUCT")
@EqualsAndHashCode(of = {"id"})
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
    private List<ProductImageEntity> images = new ArrayList<>();

    @ManyToMany(mappedBy="products",cascade = CascadeType.ALL)
    private List<CategoryEntity> categories = new ArrayList<>();


}
