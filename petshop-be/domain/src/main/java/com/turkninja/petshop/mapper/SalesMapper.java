package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.sales.SalesGetResponse;
import com.turkninja.petshop.entity.sales.SalesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesMapper {
    SalesGetResponse entityToGetResponse(SalesEntity entity);
}