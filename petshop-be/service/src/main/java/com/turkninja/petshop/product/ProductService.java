package com.turkninja.petshop.product;

import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    public GetProductResponse getCategoryById(Long id);

    public Page<GetProductResponse> list();

    public CreateProductResponse create(CreateProductRequest product);

    public UpdateProductResponse update(UpdateProductRequest product);

    public void delete(Long id);
}
