package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.product.ProductImageEntity;

public interface ProductImageRepository extends ExtendedQueryDslJpaRepository<ProductImageEntity, Long> {
}
