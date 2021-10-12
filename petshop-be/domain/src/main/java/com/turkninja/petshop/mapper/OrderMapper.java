package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderGetResponse entityToGetResponse(OrderEntity entity);
}