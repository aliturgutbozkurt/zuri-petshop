package com.turkninja.petshop.api.response.product;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class CreateProductResponse {

    private Long id;
    private String name;
    private String about;
    private double oldPrice;
    private double price;
    private Set<GetProductImageResponse> images;
    private Long categoryId;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime revisedAt;
}
