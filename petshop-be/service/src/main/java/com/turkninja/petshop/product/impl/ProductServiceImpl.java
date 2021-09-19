package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.ProductCategoryRepository;
import com.turkninja.petshop.ProductRepository;
import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
    public GetProductResponse getProductById(Long id) {
        Optional<ProductEntity> productOptional =
                productRepository.findByIdAndActiveTrue(id);
        if(productOptional.isPresent()){
            return  productMapper.entityToGetResponse(productOptional.get());
        }
        return null;
    }

    @Override
    public PageResponse<GetProductResponse> list(int page, int size) {
        Page<ProductEntity> productEntities =
                productRepository.findAllByActiveTrue(PageRequest.of(page, size, Sort.by("name")));
        return productMapper.pageEntitiesToGetPageResponse(productEntities);
    }

    @Override
    public CreateProductResponse create(UpsertProductRequest request) {
        ProductCategoryEntity category = null;
        if (Objects.nonNull(request.getCategoryId())) {
            category = productCategoryRepository.findByIdAndActiveTrue(request.getCategoryId()).orElseThrow(() ->
                    new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                            AppParameter.get("categoryId", request.getCategoryId())));
            checkCategoryHasSubCategories(category);
        }
        ProductEntity entity = productMapper.upsertRequestToEntity(request);
        entity.setCategory(category);
        entity.setProductToImage();
        return productMapper.entityToCreateResponse(productRepository.save(entity));
    }

    private void checkCategoryHasSubCategories(ProductCategoryEntity category) {
        if (category.getSubCategories().size() > 0) {
            throw new ApplicationException(AppMessage.CATEGORY_ALREADY_HAVE_CATEGORIES,
                    AppParameter.get("categoryId", category.getId()));
        }
    }

    @Override
    public UpdateProductResponse update(UpsertProductRequest product) {
        return null;
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("productId", id)));
       productEntity.setActive(false);
       productRepository.save(productEntity);
    }
}
