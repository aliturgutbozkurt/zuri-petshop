package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.user.UserRoleEntity;

import java.util.Optional;

public interface UserRoleRepository extends ExtendedQueryDslJpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByName(String name);
}
