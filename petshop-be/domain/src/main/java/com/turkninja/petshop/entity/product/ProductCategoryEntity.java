package com.turkninja.petshop.entity.product;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "photoUrl", nullable = false)
    private String photoUrl;

    @Column(name = "about")
    private String about;

    @Column(name = "depth", nullable = false, columnDefinition = "int default 0")
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id")
    private ProductCategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductCategoryEntity> subCategories = new HashSet<ProductCategoryEntity>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductEntity> products;
}