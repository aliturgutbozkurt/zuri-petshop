package com.turkninja.petshop.question;

import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;
import com.turkninja.petshop.entity.question.QuestionEntity;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

public interface QuestionService {
    public CreateQuestionResponse createQuestion(CreateQuestionRequest request);
    public GetQuestionResponse GetQuestionById(long questionId);
}
