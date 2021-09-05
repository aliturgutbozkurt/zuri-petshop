package com.turkninja.petshop.product;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    public GetProductResponse getCategoryById(Long id);

    public Page<GetProductResponse> list();

    public CreateProductResponse create(UpsertProductRequest product);

    public UpdateProductResponse update(UpsertProductRequest product);

    public void delete(Long id);
}
