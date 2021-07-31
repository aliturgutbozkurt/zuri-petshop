package com.turkninja.petshop.product;

import com.turkninja.petshop.api.request.product.CreateCategoryRequest;
import com.turkninja.petshop.api.request.product.UpdateCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {

    public GetSoleCategoryResponse getCategoryById(Long id);

    public PageResponse<GetCategoryResponse> list(int page, int size);

    List<GetCategoryResponse> getCategoryByParentId(Long parentId);

    public CreateCategoryResponse create(CreateCategoryRequest category);

    public UpdateCategoryResponse update(UpdateCategoryRequest category);

    public void delete(Long id);

}
