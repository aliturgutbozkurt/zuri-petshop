package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderItemEntity;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends ExtendedQueryDslJpaRepository<OrderItemEntity, Long> {
    Optional<OrderItemEntity> findByIdAndActiveTrue(Long id);

    List<OrderItemEntity> findByOrderIdAndActiveTrue(Long orderId);

    Optional<OrderItemEntity> findByOrderIdAndProductIdAndActiveTrue(Long orderId, Long productId);
}