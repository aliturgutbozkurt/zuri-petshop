package com.turkninja.petshop.api.response.order;

import com.turkninja.petshop.api.response.product.GetProductResponse;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemGetResponse {
    private GetProductResponse product;
    private Integer count;
}
