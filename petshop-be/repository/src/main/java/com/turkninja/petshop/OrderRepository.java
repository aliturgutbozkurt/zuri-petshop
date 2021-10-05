package com.turkninja.petshop;

import com.turkninja.petshop.api.request.order.OrderSearchRequest;
import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrderRepository extends ExtendedQueryDslJpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndActiveTrue(Long id);

    Optional<OrderEntity> findByNumberAndActiveTrue(int number);

    Page<OrderEntity> findAllByActiveTrue(PageRequest pageRequest);

    Stream<OrderEntity> search(OrderSearchRequest searchCriteria, Pageable pageable);

    Long count(OrderSearchRequest searchRequest);
}