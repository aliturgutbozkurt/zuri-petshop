package com.turkninja.petshop.question;

import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;

public interface QuestionService {
     CreateQuestionResponse createQuestion(CreateQuestionRequest request);
     GetQuestionResponse GetQuestionById(long questionId);
     PageResponse<GetQuestionResponse> getAllQuestions(int page,int size);
}
