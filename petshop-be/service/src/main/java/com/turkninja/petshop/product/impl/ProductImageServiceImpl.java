package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.*;
import com.turkninja.petshop.product.ProductImageService;
import com.turkninja.petshop.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Override
    public GetProductImageResponse getCategoryById(Long id) {
        return null;
    }

    @Override
    public Page<GetProductImageResponse> list() {
        return null;
    }

    @Override
    public CreateProductImageResponse create(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
