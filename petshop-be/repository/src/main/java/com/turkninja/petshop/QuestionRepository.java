package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.question.QuestionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ExtendedQueryDslJpaRepository<QuestionEntity,Long> {
      QuestionEntity getOne(Long id);
}
