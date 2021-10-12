package com.turkninja.petshop.api.response.question;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class GetQuestionResponse {
    private long id;
    private String explanation;
    private String userName;
    private LocalDateTime createdAt;
    private String createdBy;
}
