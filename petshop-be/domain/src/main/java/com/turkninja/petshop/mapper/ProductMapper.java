package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductImageMapper.class})
public interface ProductMapper {

    ProductEntity createRequestToEntity(CreateProductRequest categoryRequest);

    ProductEntity updateRequestToEntity(UpdateProductRequest categoryRequest);

    GetProductResponse entityToGetResponse(ProductEntity entity);

    @Mapping(source = "category.id", target = "categoryId")
    CreateProductResponse entityToCreateResponse(ProductEntity entity);

    UpdateProductResponse entityToUpdateResponse(ProductEntity entity);
}
