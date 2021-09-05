package com.turkninja.petshop.validation.product;

import com.turkninja.petshop.api.request.product.UpsertCategoryRequest;
import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.validation.util.ValidationUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author ali turgut bozkurt
 * Created at 9/5/2021
 */

@Component
public class ProductCategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UpsertProductRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpsertCategoryRequest request = (UpsertCategoryRequest) target;
        validateName(request.getName(), errors);
        valdidateDepth(request.getDepth(), errors);
    }

    private void validateName(String name, Errors errors) {
        if (ValidationUtil.isEmptyOrNull(name)) {
            errors.rejectValue("name", null, "İsim alanı boş olamaz");
        }
    }

    private void valdidateDepth(int depth, Errors errors) {
        if (depth < 0) {
            errors.rejectValue("depth", null, "Derinlik sıfırdan küçük olamaz");
        }
    }
}
