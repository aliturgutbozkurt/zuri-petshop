package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.product.ProductEntity;

public interface ProductRepository extends ExtendedQueryDslJpaRepository<ProductEntity, Long> {
}
