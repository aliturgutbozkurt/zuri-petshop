package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderItemGetResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.order.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserAddressMapper.class})
public interface OrderMapper {
    @Mapping(source = "entity.userAddress", target = "userAddress")
    OrderGetResponse entityToGetResponse(OrderEntity entity);
}