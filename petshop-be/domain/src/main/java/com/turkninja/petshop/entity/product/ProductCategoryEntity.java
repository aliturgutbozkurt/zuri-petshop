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

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "photoUrl",nullable = false)
    private String photoUrl;

    @Column(name = "about")
    private String about;

    @Column(name = "depth", nullable = false, columnDefinition = "int default 0")
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="parent_id")
    private ProductCategoryEntity parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductCategoryEntity> subCategories = new HashSet<ProductCategoryEntity>();

    @OneToMany(mappedBy="category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductEntity> products;
}