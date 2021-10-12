package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.order.OrderItemGetResponse;
import com.turkninja.petshop.entity.order.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {
    @Mapping(source = "entity.product", target = "product")
    OrderItemGetResponse entityToGetResponse(OrderItemEntity entity);
}
