package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository extends ExtendedQueryDslJpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByActiveTrue(Pageable pageRequest);

    Optional<ProductEntity> findByIdAndActiveTrue(Long id);
}
