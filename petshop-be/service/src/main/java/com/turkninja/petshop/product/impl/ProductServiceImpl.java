package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.ProductCategoryRepository;
import com.turkninja.petshop.ProductRepository;
import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.ProductMapper;
import com.turkninja.petshop.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public GetProductResponse getCategoryById(Long id) {
        return null;
    }

    @Override
    public Page<GetProductResponse> list() {
        return null;
    }

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        ProductCategoryEntity category = null;
        if (Objects.nonNull(request.getCategoryId())) {
            category = productCategoryRepository.findByIdAndActiveTrue(request.getCategoryId()).orElseThrow(() ->
                    new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                            AppParameter.get("categoryId", request.getCategoryId())));
            checkCategoryHasSubCategories(category);
        }
        ProductEntity entity = productMapper.createRequestToEntity(request);
        entity.setCategory(category);
        return productMapper.entityToCreateResponse(productRepository.save(entity));
    }

    private void checkCategoryHasSubCategories(ProductCategoryEntity category) {
        if(category.getSubCategories().size()>0){
            throw new ApplicationException(AppMessage.CATEGORY_ALREADY_HAVE_CATEGORIES,
                    AppParameter.get("categoryId", category.getId()));
        }
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
