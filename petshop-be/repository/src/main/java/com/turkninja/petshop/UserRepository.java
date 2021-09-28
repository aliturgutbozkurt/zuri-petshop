package com.turkninja.petshop;


import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.Optional;

public interface UserRepository extends ExtendedQueryDslJpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findAllByActiveTrue(@PageableDefault() Pageable pageRequest);
    Optional<UserEntity> findByIdAndActiveTrue(Long id);
    Optional<UserEntity> findByIdAndActiveFalse(Long id);
}
