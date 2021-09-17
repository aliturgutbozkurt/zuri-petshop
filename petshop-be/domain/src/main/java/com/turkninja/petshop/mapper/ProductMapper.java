package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductImageMapper.class, ProductCategoryMapper.class})
public interface ProductMapper {

    ProductEntity upsertRequestToEntity(UpsertProductRequest categoryRequest);

    GetProductResponse entityToGetResponse(ProductEntity entity);

    @Mapping(source = "category.id", target = "categoryId")
    CreateProductResponse entityToCreateResponse(ProductEntity entity);

    UpdateProductResponse entityToUpdateResponse(ProductEntity entity);

    default PageResponse<GetProductResponse> pageEntitiesToGetPageResponse(Page<ProductEntity> productEntities){
        PageResponse<GetProductResponse> response = new PageResponse<GetProductResponse>();
        List<ProductEntity> content = productEntities.getContent();
        response.setContent(content.stream().map(ce->this.entityToGetResponse(ce)).collect(Collectors.toList()));
        response.setTotalPages(productEntities.getTotalPages());
        response.setTotalElements(productEntities.getTotalElements());
        return response;
    }
}
