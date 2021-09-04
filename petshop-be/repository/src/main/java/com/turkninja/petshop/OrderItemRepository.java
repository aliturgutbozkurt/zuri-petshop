package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderItemEntity;

public interface OrderItemRepository extends ExtendedQueryDslJpaRepository<OrderItemEntity, Long> {
}
