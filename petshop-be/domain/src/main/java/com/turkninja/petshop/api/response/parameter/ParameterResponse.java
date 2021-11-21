package com.turkninja.petshop.api.response.parameter;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ParameterResponse {
    private long id;
    private String parameterKey;
    private String parameterValue;
    private String parameterType;
}
