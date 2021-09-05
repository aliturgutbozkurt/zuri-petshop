package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.product.ProductService;
import com.turkninja.petshop.validation.product.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ali turgut bozkurt
 * Created at 8/13/2021
 */

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    private final ProductValidator productValidator;

    public ProductResource(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @PostMapping
    public ResponseEntity<Object> create(
            @Valid @RequestBody UpsertProductRequest request,
            BindingResult bindingResult) {

        productValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CreateProductResponse response = productService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
