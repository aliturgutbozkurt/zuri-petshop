package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface OrderRepository extends ExtendedQueryDslJpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndActiveTrue(Long id);

    Optional<OrderEntity> findByNumberAndActiveTrue(int number);

    Page<OrderEntity> findAllByActiveTrue(PageRequest pageRequest);
}