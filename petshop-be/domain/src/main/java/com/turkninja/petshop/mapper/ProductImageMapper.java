package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.CreateProductImageRequest;
import com.turkninja.petshop.api.response.product.CreateProductImageResponse;
import com.turkninja.petshop.api.response.product.GetProductImageResponse;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.entity.product.ProductImageEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImageEntity createRequestToEntity(CreateProductImageRequest request);

    GetProductImageResponse entityToGetResponse(ProductImageEntity entity);

    CreateProductImageResponse entityToCreateResponse(ProductImageEntity entity);

    Set<ProductImageEntity> createImageRequestSetToEntitySet(Set<CreateProductImageRequest> createRequestSet);
}
