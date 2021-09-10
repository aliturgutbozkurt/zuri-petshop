package com.turkninja.petshop.api.request.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class UpsertCategoryRequest {

    private Long id;
    private String name;
    private String photoUrl;
    private Long parentId;
    private int depth;
}
