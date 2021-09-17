package com.turkninja.petshop.api.response.product;

import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.entity.product.ProductImageEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class GetProductResponse {

    private Long id;
    private String name;
    private String createdBy;
    private String about;
    private double oldPrice;
    private double price;
    private Set<GetProductImageResponse> images = new HashSet<>();
    private GetSoleCategoryResponse category;
    private LocalDateTime createdAt;
    private LocalDateTime revisedAt;
}
