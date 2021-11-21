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

    @NotNull
    private Long productId;
}
