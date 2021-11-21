package com.turkninja.petshop.answer;

import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import com.turkninja.petshop.api.response.answer.CreateAnswerResponse;
import com.turkninja.petshop.api.response.answer.GetAllAnswerResponse;


import java.util.List;

public interface AnswerService {
    CreateAnswerResponse createAnswer(CreateAnswerRequest answer);
    List<GetAllAnswerResponse> getAllAnswerByQuestionId(long questionId);
}
