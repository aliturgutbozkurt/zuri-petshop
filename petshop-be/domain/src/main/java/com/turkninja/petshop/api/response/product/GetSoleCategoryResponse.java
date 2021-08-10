package com.turkninja.petshop.api.response.product;

import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class GetSoleCategoryResponse {
    private Long id;
    private String name;
    private GetSoleCategoryResponse parent;
    private String createdBy;
    private int depth;
    private LocalDateTime createdAt;
    private LocalDateTime revisedAt;
}
