package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.applicationexception.AppExceptionEntity;

import java.util.Optional;

public interface AppExceptionRepository extends ExtendedQueryDslJpaRepository<AppExceptionEntity, String> {

    Optional<AppExceptionEntity> findByCode(String code);
}
