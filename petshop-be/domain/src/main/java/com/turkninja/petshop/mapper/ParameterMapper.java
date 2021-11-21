package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.parameter.ParameterResponse;
import com.turkninja.petshop.entity.parameter.ParameterEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ParameterMapper {
    ParameterResponse parameterEntityToDto(ParameterEntity parameterEntity);

    List<ParameterResponse> parameterEntityToDto(List<ParameterEntity> entities);

}