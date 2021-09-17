package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.user.UserAddressEntity;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends ExtendedQueryDslJpaRepository<UserAddressEntity, Long> {
    Optional<UserAddressEntity> findByIdAndActiveTrue(Long id);

    List<UserAddressEntity> findAllByUserIdAndActiveTrue(Long userId);
}