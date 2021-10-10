package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity createRequestToEntity(OrderCreateRequest request);

    OrderGetResponse entityToGetResponse(OrderEntity entity);

    OrderCreateResponse entityToCreateResponse(OrderEntity entity);

    OrderUpdateResponse entityToUpdateResponse(OrderEntity entity);
}