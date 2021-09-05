package com.turkninja.petshop.validation.product;

import com.turkninja.petshop.api.request.product.CreateProductImageRequest;
import com.turkninja.petshop.api.request.product.UpsertCategoryRequest;
import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.validation.util.ValidationUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 9/5/2021
 */

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UpsertProductRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpsertProductRequest request = (UpsertProductRequest) target;
        validateName(request.getName(), errors);
        valdidateCategory(request.getCategoryId(), errors);
        validateAbout(request.getAbout(), errors);
        validateImages(request.getImages(), errors);
    }

    private void validateImages(Set<CreateProductImageRequest> images, Errors errors) {
        if (images.size() < 1) {
            errors.rejectValue("images", null, "En az bir resim olmalı");
        }
        for (CreateProductImageRequest image : images) {
            if (ValidationUtil.isEmptyOrNull(image.getUrl())) {
                errors.rejectValue("images", null, "Resim alanında url boş olamaz.");
            }
            if (ValidationUtil.isNullOrNegative(Long.valueOf(image.getOrderNumber()))) {
                errors.rejectValue("images", null, "Resim sıra numarasaı sıfırdan büyük bir değer olmalı");
            }
        }
    }

    private void validateAbout(String about, Errors errors) {
        if (ValidationUtil.isEmptyOrNull(about)) {
            errors.rejectValue("name", null, "Hakkında alanı boş olamaz");
        }
    }

    private void valdidateCategory(Long categoryId, Errors errors) {
        if (ValidationUtil.isNullOrNegative(categoryId)) {
            errors.rejectValue("name", null, "Kategori Id alanı doğru değil");
        }
    }

    private void validateName(String name, Errors errors) {
        if (ValidationUtil.isEmptyOrNull(name)) {
            errors.rejectValue("name", null, "İsim alanı boş olamaz");
        }
    }

}
