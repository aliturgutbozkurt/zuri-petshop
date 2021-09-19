package com.turkninja.petshop.api.request.answer;

import lombok.Data;

@Data
public class CreateAnswerRequest {
    public Long questionId;
    private String explanation;
}

