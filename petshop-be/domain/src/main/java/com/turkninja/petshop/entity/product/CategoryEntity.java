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
@Table(name = "CATEGORY")
@EqualsAndHashCode(of = {"id"})
@Data
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_PARENT_ID")
    private CategoryEntity parent;

    @ManyToMany
    @JoinTable(name = "CATEGORY", joinColumns = {@JoinColumn(name = "CATEGORY_PARENT_ID")}, inverseJoinColumns = {@JoinColumn(name = "ID")})
    private List<CategoryEntity> subCategories = new ArrayList<CategoryEntity>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CATEGORY_PRODUCT"
            , joinColumns = {
            @JoinColumn(name = "CATEGORY_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "PRODUCT_ID")
    }
    )
    private List<ProductEntity> products;
}