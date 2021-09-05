package com.turkninja.petshop.api.request.product;

import lombok.Data;

import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class UpsertProductRequest {

    private Long id;
    private String name;
    private String about;
    private Set<CreateProductImageRequest> images;
    private Long categoryId;
}
