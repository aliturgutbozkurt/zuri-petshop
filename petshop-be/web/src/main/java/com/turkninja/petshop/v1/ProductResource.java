package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.product.UpsertProductRequest;
import com.turkninja.petshop.api.response.product.CreateProductResponse;
import com.turkninja.petshop.api.response.product.GetProductResponse;
import com.turkninja.petshop.api.response.product.ProductSearchCriteria;
import com.turkninja.petshop.api.response.product.UpdateProductResponse;
import com.turkninja.petshop.product.ProductService;
import com.turkninja.petshop.validation.product.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductResource {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ProductService productService;
    private final ProductValidator productValidator;

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable("productId") Long id) {
        GetProductResponse response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<GetProductResponse>> search(
            @RequestBody @Valid ProductSearchCriteria searchCriteria,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "category"),
                    @SortDefault(sort = "name")}) Pageable pageable) {
        Page<GetProductResponse> response = productService.search(searchCriteria, pageable);
        return ResponseEntity.ok(response);
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

    @PutMapping
    public ResponseEntity<Object> update(
            @Valid @RequestBody UpsertProductRequest request,
            BindingResult bindingResult) {

        productValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        UpdateProductResponse response = productService.update(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> delete(@PathVariable("productId") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
