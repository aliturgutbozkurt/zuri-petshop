package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.product.UpsertCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.api.response.product.GetSoleCategoryResponse;
import com.turkninja.petshop.api.response.product.UpdateCategoryResponse;
import com.turkninja.petshop.product.CategoryService;
import com.turkninja.petshop.validation.product.ProductCategoryValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ali turgut bozkurt
 * Created at 7/31/2021
 */

@RestController
@RequestMapping("/api/v1/category")
public class ProductCategoryResource {

    private final CategoryService categoryService;

    private final ProductCategoryValidator productCategoryValidator;

    public ProductCategoryResource(CategoryService categoryService, ProductCategoryValidator productCategoryValidator) {
        this.categoryService = categoryService;
        this.productCategoryValidator = productCategoryValidator;
    }

    @GetMapping("/listByParentId")
    public ResponseEntity<List<GetCategoryResponse>> listCategoriesByParentId(
            @RequestParam(required = false, value = "parentId") Long parentId) {
        List<GetCategoryResponse> response = categoryService.getCategoryByParentId(parentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pageByParentId")
    public ResponseEntity<PageResponse<GetCategoryResponse>> pageCategoriesByParentId(
            @RequestParam(required = false, value = "parentId") Long parentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        PageResponse<GetCategoryResponse> response = categoryService.getCategoryByParentId(parentId, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<GetSoleCategoryResponse> getById(
            @PathVariable("categoryId") Long id) {
        GetSoleCategoryResponse response = categoryService.getCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponse<GetCategoryResponse>> listPage(
            @RequestParam("page") int page, @RequestParam("size") int size) {
        PageResponse<GetCategoryResponse> response = categoryService.list(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid UpsertCategoryRequest request,
                                         BindingResult bindingResult) {
        productCategoryValidator.validate(request,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CreateCategoryResponse response = categoryService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UpsertCategoryRequest request,
                                         BindingResult bindingResult) {
        productCategoryValidator.validate(request,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        UpdateCategoryResponse response = categoryService.update(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> delete(@PathVariable("categoryId") Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
