package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.enums.OrderState;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends ExtendedQueryDslJpaRepository<OrderEntity, String> {
    Optional<OrderEntity> findByNumber(String number);

    List<OrderEntity> findByUserId(Long userId);

    Optional<OrderEntity> findByUserIdAndState(Long userId, OrderState state);
}