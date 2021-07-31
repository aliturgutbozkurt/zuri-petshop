package com.turkninja.petshop.api.response.exception;

import com.turkninja.petshop.exception.AppMessageDescription;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiExceptionResponse {

    private long timestamp;

    private String code;

    private HttpStatus statusCode;

    private AppMessageDescription description;
}
