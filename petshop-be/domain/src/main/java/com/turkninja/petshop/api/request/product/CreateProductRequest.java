package com.turkninja.petshop.api.request.product;

import lombok.Data;

import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class CreateProductRequest {

    private String name;
    private String about;
    private Set<String> imageUrls;
    private Long categoryId;
}
