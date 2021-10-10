package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.response.order.*;
import com.turkninja.petshop.entity.order.OrderItemEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductEntity.class})
public interface OrderItemMapper {
    OrderItemGetResponse entityToGetResponse(OrderItemEntity entity);
}
