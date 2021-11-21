package com.turkninja.petshop.parameter;

import com.turkninja.petshop.api.request.parameter.UpdateParameterRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.parameter.ParameterResponse;

import java.util.List;

public interface ParameterService {
    ParameterResponse createParameter(String key,String value);
    List<ParameterResponse> getAllParameters();
    ParameterResponse updateParameter(UpdateParameterRequest updateParameterRequest);
    ParameterResponse getParameterById(Long id);
}
