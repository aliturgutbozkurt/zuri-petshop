package com.turkninja.petshop.api.response.question;

import lombok.Data;

@Data
public class GetQuestionResponse {
    private long id;
    private String explanation;
    private String userName;
}
