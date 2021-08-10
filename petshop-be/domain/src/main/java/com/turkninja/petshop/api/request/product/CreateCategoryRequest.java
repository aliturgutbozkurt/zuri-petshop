package com.turkninja.petshop.api.request.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class CreateCategoryRequest {

    @NotNull
    private String name;

    private Long parentId;

    private int depth;
}
