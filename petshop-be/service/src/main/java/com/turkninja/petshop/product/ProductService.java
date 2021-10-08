package com.turkninja.petshop.product;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    GetProductResponse getProductById(Long id);

    PageResponse<GetProductResponse> list(int page, int size);

    PageResponse<GetProductResponse> list(int page, int size, Long categoryId);

    CreateProductResponse create(UpsertProductRequest product);

    UpdateProductResponse update(UpsertProductRequest product);

    void delete(Long id);
}
