package com.turkninja.petshop.api.response.product;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchCriteria {
    private String name;
    private BigDecimal oldPrice;
    private BigDecimal price;
    private Long categoryId;
}
