package com.turkninja.petshop.api.response.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String photoUrl;
    @JsonIgnoreProperties({"photoUrl","createdBy","depth","createdAt","revisedAt"})
    private GetSoleCategoryResponse parent;
    private String createdBy;
    private int depth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime revisedAt;
}
