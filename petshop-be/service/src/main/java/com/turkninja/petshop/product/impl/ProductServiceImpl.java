package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public GetProductResponse getCategoryById(Long id) {
        return null;
    }

    @Override
    public Page<GetProductResponse> list() {
        return null;
    }

    @Override
    public CreateProductResponse create(CreateProductRequest product) {
        return null;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
