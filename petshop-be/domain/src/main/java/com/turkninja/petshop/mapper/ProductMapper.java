package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.product.CreateProductRequest;
import com.turkninja.petshop.api.request.product.UpdateProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.entity.product.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {ProductImageMapper.class})
public interface ProductMapper {

    default ProductEntity createRequestToEntity(CreateProductRequest categoryRequest) {
        if (Objects.isNull(categoryRequest)) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setName(categoryRequest.getName());
        entity.setAbout(categoryRequest.getAbout());
        categoryRequest.getImageUrls().forEach(url -> {
            ProductImageEntity imageEntity = new ProductImageEntity();
            imageEntity.setUrl(url);
            imageEntity.setProduct(entity);
            entity.addImage(imageEntity);
        });
        return entity;
    }

    ProductEntity updateRequestToEntity(UpdateProductRequest categoryRequest);

    GetProductResponse entityToGetResponse(ProductEntity entity);

    @Mapping(source="category.id",target="categoryId")
    CreateProductResponse entityToCreateResponse(ProductEntity entity);

    UpdateProductResponse entityToUpdateResponse(ProductEntity entity);
}
