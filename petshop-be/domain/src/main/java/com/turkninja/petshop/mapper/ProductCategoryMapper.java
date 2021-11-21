package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.UpsertCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryEntity upsertRequestToEntity(UpsertCategoryRequest categoryRequest);
    GetCategoryResponse entityToGetResponse(ProductCategoryEntity entity);
    CreateCategoryResponse entityToCreateResponse(ProductCategoryEntity entity);
    UpdateCategoryResponse entityToUpdateResponse(ProductCategoryEntity entity);

    default PageResponse<GetCategoryResponse> pageEntitiesToGetPageResponse(Page<ProductCategoryEntity> entities){
        PageResponse<GetCategoryResponse> response = new PageResponse<GetCategoryResponse>();
        List<ProductCategoryEntity> content = entities.getContent();
        response.setContent(content.stream().map(ce->this.entityToGetResponse(ce)).collect(Collectors.toList()));
        response.setTotalPages(entities.getTotalPages());
        response.setTotalElements(entities.getTotalElements());
        return response;
    }

    default List<GetCategoryResponse> listEntitesToGetListResponse (List<ProductCategoryEntity> entities){
        return entities.stream().map(ce->this.entityToGetResponse(ce)).collect(Collectors.toList());
    }

    GetSoleCategoryResponse entityToGetSoleResponse(ProductCategoryEntity productCategoryEntity);
}
