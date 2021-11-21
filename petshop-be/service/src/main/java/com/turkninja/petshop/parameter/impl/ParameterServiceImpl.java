package com.turkninja.petshop.parameter.impl;

import com.turkninja.petshop.ParameterRepository;
import com.turkninja.petshop.api.request.parameter.UpdateParameterRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.parameter.ParameterResponse;
import com.turkninja.petshop.entity.parameter.ParameterEntity;
import com.turkninja.petshop.mapper.ParameterMapper;
import com.turkninja.petshop.parameter.ParameterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParameterServiceImpl implements ParameterService {
    private final ParameterRepository parameterRepository;
    private final ParameterMapper parameterMapper;

    public ParameterServiceImpl(ParameterRepository parameterRepository, ParameterMapper parameterMapper) {
        this.parameterRepository = parameterRepository;
        this.parameterMapper = parameterMapper;
    }

    @Override
    public ParameterResponse createParameter(String key, String value) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setParameterKey(key);
        parameterEntity.setParameterValue(value);

        ParameterResponse parameterResponse = parameterMapper.parameterEntityToDto(parameterRepository.save(parameterEntity));
        return parameterResponse;
    }

    @Override
    public List<ParameterResponse> getAllParameters() {
        List<ParameterEntity> parameters = parameterRepository.findAll();
        return parameterMapper.parameterEntityToDto(parameters);
    }

    @Override
    public ParameterResponse updateParameter(UpdateParameterRequest updateParameterRequest) {
       ParameterEntity parameterEntity = parameterRepository.getOne(updateParameterRequest.getId());
       if(Objects.nonNull(parameterEntity.getId())){
           parameterEntity.setParameterKey(updateParameterRequest.getKey());
           parameterEntity.setParameterValue(updateParameterRequest.getValue());
       }
      return parameterMapper.parameterEntityToDto(parameterRepository.save(parameterEntity));
    }

    @Override
    public ParameterResponse getParameterById(Long id) {
        ParameterEntity parameterEntity = parameterRepository.getOne(id);
        return parameterMapper.parameterEntityToDto(parameterEntity);
    }
}
