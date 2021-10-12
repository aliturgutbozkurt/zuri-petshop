package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.question.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends ExtendedQueryDslJpaRepository<QuestionEntity,Long> {
      QuestionEntity getOne(Long id);
}
