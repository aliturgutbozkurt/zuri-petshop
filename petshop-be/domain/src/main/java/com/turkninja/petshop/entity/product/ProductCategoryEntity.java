package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Entity
@Table(name = "CATEGORY")
@EqualsAndHashCode(of = {"id"})
@Data
public class ProductCategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "depth", nullable = false, columnDefinition = "int default 0")
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="parent_id")
    private ProductCategoryEntity parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<ProductCategoryEntity> subCategories = new HashSet<ProductCategoryEntity>();

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