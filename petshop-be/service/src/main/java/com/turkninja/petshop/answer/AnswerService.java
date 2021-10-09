package com.turkninja.petshop.answer;

import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import com.turkninja.petshop.api.response.answer.CreateAnswerResponse;

public interface AnswerService {
    CreateAnswerResponse createAnswer(CreateAnswerRequest answer);
}
