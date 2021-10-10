package com.turkninja.petshop.api.request.order;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductAddRequest {
    @NotNull
    private Long userId;

    private Long orderId;

    @NotNull
    private Long productId;
}
