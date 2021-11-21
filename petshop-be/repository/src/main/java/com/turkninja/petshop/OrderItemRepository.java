package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderItemEntity;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends ExtendedQueryDslJpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrderNumberAndActiveTrue(String orderNumber);

    Optional<OrderItemEntity> findByOrderNumberAndProductIdAndActiveTrue(String number, Long productId);
}