package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.ProductCategoryRepository;
import com.turkninja.petshop.ProductRepository;
import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.ProductSearchCriteria;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.ProductMapper;
import com.turkninja.petshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public GetProductResponse getProductById(Long id) {
        Optional<ProductEntity> productOptional =
                productRepository.findByIdAndActiveTrue(id);
        if (productOptional.isPresent()) {
            return productMapper.entityToGetResponse(productOptional.get());
        }
        return null;
    }

    @Override
    public Page<GetProductResponse> search(ProductSearchCriteria searchCriteria, Pageable pageable) {
        Example<ProductEntity> example = createExample(searchCriteria);

        List<GetProductResponse> products = productRepository.findAll(example, pageable).stream()
                .map(productMapper::entityToGetResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(products);
    }

    @Override
    public CreateProductResponse create(UpsertProductRequest request) {
        ProductEntity entity = getProductEntityForUpsert(request);
        return productMapper.entityToCreateResponse(productRepository.save(entity));
    }

    @Override
    public UpdateProductResponse update(UpsertProductRequest request) {
        Optional<ProductEntity> productOptional = productRepository.findByIdAndActiveTrue(request.getId());
        if (!productOptional.isPresent()) {
            throw new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                    AppParameter.get("productId", request.getId()));
        }
        ProductEntity entity = getProductEntityForUpsert(request);
        return productMapper.entityToUpdateResponse(productRepository.save(entity));
    }

    private ProductEntity getProductEntityForUpsert(UpsertProductRequest request) {
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
        return entity;
    }

    private void checkCategoryHasSubCategories(ProductCategoryEntity category) {
        if (category.getSubCategories().size() > 0) {
            throw new ApplicationException(AppMessage.CATEGORY_ALREADY_HAVE_CATEGORIES,
                    AppParameter.get("categoryId", category.getId()));
        }
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("productId", id)));
        productEntity.setActive(false);
        productRepository.save(productEntity);
    }

    private Example<ProductEntity> createExample(ProductSearchCriteria searchCriteria){
        Optional<ProductCategoryEntity> productCategory = Optional.empty();
        if(searchCriteria.getCategoryId() != null){
            productCategory = productCategoryRepository.findByIdAndActiveTrue(searchCriteria.getCategoryId());

        }

        ProductEntity product = ProductEntity.builder()
                .name(searchCriteria.getName())
                .price(searchCriteria.getPrice())
                .category(productCategory.isPresent()? productCategory.get() : null)
                .build();

        return Example.of(product);
    }
}
