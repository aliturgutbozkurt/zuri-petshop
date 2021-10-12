package com.turkninja.petshop.api.response.answer;

import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.entity.user.UserEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GetAllAnswerResponse {
    private String explanation;
    private long questionId;
    private UserEntity user;
}
