package com.turkninja.petshop;


import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends ExtendedQueryDslJpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
