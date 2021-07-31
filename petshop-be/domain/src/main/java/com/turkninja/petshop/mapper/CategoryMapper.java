package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.CreateCategoryRequest;
import com.turkninja.petshop.api.request.product.UpdateCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;
import com.turkninja.petshop.entity.product.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity createRequestToEntity(CreateCategoryRequest categoryRequest);
    CategoryEntity updateRequestToEntity(UpdateCategoryRequest categoryRequest);
    GetCategoryResponse entityToGetResponse(CategoryEntity entity);
    CreateCategoryResponse entityToCreateResponse(CategoryEntity entity);
    UpdateCategoryResponse entityToUpdateResponse(CategoryEntity entity);

    default PageResponse<GetCategoryResponse> pageEntitiesToGetPageResponse(Page<CategoryEntity> entities){
        PageResponse<GetCategoryResponse> response = new PageResponse<GetCategoryResponse>();
        List<CategoryEntity> content = entities.getContent();
        response.setContent(content.stream().map(ce->this.entityToGetResponse(ce)).collect(Collectors.toList()));
        response.setTotalPages(entities.getTotalPages());
        response.setTotalElements(entities.getTotalElements());
        return response;
    }

    default List<GetCategoryResponse> listEntitesToGetListResponse (List<CategoryEntity> entities){
        return entities.stream().map(ce->this.entityToGetResponse(ce)).collect(Collectors.toList());
    }

    GetSoleCategoryResponse entityToGetSoleResponse(CategoryEntity categoryEntity);
}
