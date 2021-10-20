package com.turkninja.petshop.product;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.ProductSearchCriteria;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    GetProductResponse getProductById(Long id);

    Page<GetProductResponse> search(ProductSearchCriteria searchCriteria, Pageable pageable);

    CreateProductResponse create(UpsertProductRequest product);

    UpdateProductResponse update(UpsertProductRequest product);

    void delete(Long id);
}
