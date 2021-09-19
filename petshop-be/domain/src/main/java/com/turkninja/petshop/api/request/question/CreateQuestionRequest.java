package com.turkninja.petshop.api.request.question;

import com.turkninja.petshop.enums.QuestionType;
import lombok.Data;

@Data
public class CreateQuestionRequest {
    private String explanation;
}
