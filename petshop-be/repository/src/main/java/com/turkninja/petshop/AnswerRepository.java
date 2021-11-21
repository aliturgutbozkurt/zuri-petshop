package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends ExtendedQueryDslJpaRepository<AnswerEntity,Long> {
    @Query("SELECT c FROM AnswerEntity c WHERE c.id IN  (SELECT max(b.id) FROM AnswerEntity b WHERE b.question.id = ?1)")
    AnswerEntity findTopAnswerByQuestionId(long QuestionId);

    List<AnswerEntity>  findAllByQuestionId(long questionId);

}
