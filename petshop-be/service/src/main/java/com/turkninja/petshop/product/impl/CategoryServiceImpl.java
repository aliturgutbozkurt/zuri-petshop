package com.turkninja.petshop.product.impl;

import com.turkninja.petshop.CategoryRepository;
import com.turkninja.petshop.api.request.product.CreateCategoryRequest;
import com.turkninja.petshop.api.request.product.UpdateCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;
import com.turkninja.petshop.entity.product.CategoryEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.CategoryMapper;
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

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public GetSoleCategoryResponse getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("categoryId", id)));

        return categoryMapper.entityToGetSoleResponse(categoryEntity);
    }

    @Override
    public List<GetCategoryResponse> getCategoryByParentId(Long parentId) {
        List<CategoryEntity> categoryEntities = categoryRepository.findByParentIdAndActiveTrue(parentId);

        return categoryMapper.listEntitesToGetListResponse(categoryEntities);
    }

    @Override
    public PageResponse<GetCategoryResponse> getCategoryByParentId(Long parentId, int page, int size) {
        Page<CategoryEntity> categoryEntities = null;
        if(Objects.nonNull(parentId)) {
            categoryEntities = categoryRepository
                    .findByParentIdAndActiveTrue(parentId, PageRequest.of(page, size, Sort.by("name")));
        } else {
            categoryEntities = categoryRepository.findByParentIdIsNullAndActiveTrue(PageRequest.of(page, size, Sort.by("name")));
        }
        return categoryMapper.pageEntitiesToGetPageResponse(categoryEntities);
    }

    @Override
    public PageResponse<GetCategoryResponse> list(int page, int size) {

        Page<CategoryEntity> categoryEntities = categoryRepository
                .findAllByActiveTrue(PageRequest.of(page, size, Sort.by("name")));
        return categoryMapper.pageEntitiesToGetPageResponse(categoryEntities);
    }

    @Override
    public CreateCategoryResponse create(CreateCategoryRequest request) {

        CategoryEntity parent = null;
        if (Objects.nonNull(request.getParentId())) {
            parent = categoryRepository.findByIdAndActiveTrue(request.getParentId()).orElseThrow(() ->
                    new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                            AppParameter.get("categoryId", request.getParentId())));
        }
        CategoryEntity entity = categoryMapper.createRequestToEntity(request);
        entity.setParent(parent);
        return categoryMapper.entityToCreateResponse(categoryRepository.save(entity));
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
