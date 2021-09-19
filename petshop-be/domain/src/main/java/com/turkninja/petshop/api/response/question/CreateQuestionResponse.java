package com.turkninja.petshop.api.response.question;


import com.turkninja.petshop.enums.QuestionType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class CreateQuestionResponse {
    private String explanation;
    private QuestionType questionType;
    private LocalDateTime createdAt;
    private String createdBy;
}
