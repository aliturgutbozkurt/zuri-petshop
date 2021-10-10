package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends ExtendedQueryDslJpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndActiveTrue(Long id);

    List<OrderEntity> findByUserIdAndActiveTrue(Long userId);
}