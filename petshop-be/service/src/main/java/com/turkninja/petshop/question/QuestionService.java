package com.turkninja.petshop.question;

import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.entity.question.QuestionEntity;

public interface QuestionService {
    public CreateQuestionResponse createQuestion(CreateQuestionRequest request);
}
