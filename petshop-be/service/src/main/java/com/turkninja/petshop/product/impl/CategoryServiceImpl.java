package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.ProductCategoryRepository;
import com.turkninja.petshop.api.request.product.CreateCategoryRequest;
import com.turkninja.petshop.api.request.product.UpdateCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.ProductCategoryMapper;
import com.turkninja.petshop.product.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public CategoryServiceImpl(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }


    @Override
    public GetSoleCategoryResponse getCategoryById(Long id) {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("categoryId", id)));

        return productCategoryMapper.entityToGetSoleResponse(productCategoryEntity);
    }

    @Override
    public List<GetCategoryResponse> getCategoryByParentId(Long parentId) {
        List<ProductCategoryEntity> categoryEntities = productCategoryRepository.findByParentIdAndActiveTrueOrderByNameAsc(parentId);

        return productCategoryMapper.listEntitesToGetListResponse(categoryEntities);
    }

    @Override
    public PageResponse<GetCategoryResponse> getCategoryByParentId(Long parentId, int page, int size) {
        Page<ProductCategoryEntity> categoryEntities = null;
        if (Objects.nonNull(parentId)) {
            categoryEntities = productCategoryRepository
                    .findByParentIdAndActiveTrue(parentId, PageRequest.of(page, size, Sort.by("name")));
        } else {
            categoryEntities = productCategoryRepository.findByParentIdIsNullAndActiveTrue(PageRequest.of(page, size, Sort.by("name")));
        }
        return productCategoryMapper.pageEntitiesToGetPageResponse(categoryEntities);
    }

    @Override
    public PageResponse<GetCategoryResponse> list(int page, int size) {

        Page<ProductCategoryEntity> categoryEntities = productCategoryRepository
                .findAllByActiveTrue(PageRequest.of(page, size, Sort.by("name")));
        return productCategoryMapper.pageEntitiesToGetPageResponse(categoryEntities);
    }

    @Override
    public CreateCategoryResponse create(CreateCategoryRequest request) {

        ProductCategoryEntity parent = null;
        if (Objects.nonNull(request.getParentId())) {
            parent = productCategoryRepository.findByIdAndActiveTrue(request.getParentId()).orElseThrow(() ->
                    new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                            AppParameter.get("categoryId", request.getParentId())));
            checkCategoryHasProducts(request, parent);
        }
        ProductCategoryEntity entity = productCategoryMapper.createRequestToEntity(request);
        entity.setParent(parent);
        return productCategoryMapper.entityToCreateResponse(productCategoryRepository.save(entity));
    }

    private void checkCategoryHasProducts(CreateCategoryRequest request, ProductCategoryEntity parent) {
        if (parent.getProducts().size() > 0) {
            throw new ApplicationException(AppMessage.CATEGORY_ALREADY_HAVE_PRODUCTS,
                    AppParameter.get("categoryId", request.getParentId()));
        }
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
