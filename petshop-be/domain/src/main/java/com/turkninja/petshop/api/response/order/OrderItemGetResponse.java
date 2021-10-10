package com.turkninja.petshop.api.response.order;

import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemGetResponse {
    private OrderEntity order;
    private ProductEntity product;
    private Integer count;
}
