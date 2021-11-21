package com.turkninja.petshop.api.request.parameter;


import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateParameterRequest {

    private Long id;
    private String key;
    private String value;
    private String type;
}
