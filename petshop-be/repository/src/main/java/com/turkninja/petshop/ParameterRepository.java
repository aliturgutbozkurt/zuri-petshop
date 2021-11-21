package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.parameter.ParameterEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends ExtendedQueryDslJpaRepository<ParameterEntity,Long> {
}
