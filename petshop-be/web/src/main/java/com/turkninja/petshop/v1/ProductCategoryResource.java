package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.product.CreateCategoryRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.product.CreateCategoryResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.product.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ProductCategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/listByParentId")
    public ResponseEntity<List<GetCategoryResponse>> listCategoriesByParentId(
            @RequestParam(required = false, value = "parentId") Long parentId) {
        List<GetCategoryResponse> response= categoryService.getCategoryByParentId(parentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pageByParentId")
    public ResponseEntity<PageResponse<GetCategoryResponse>> pageCategoriesByParentId(
            @RequestParam(required = false, value = "parentId") Long parentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        PageResponse<GetCategoryResponse> response= categoryService.getCategoryByParentId(parentId, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponse<GetCategoryResponse>> listPage(
            @RequestParam("page") int page, @RequestParam("size") int size) {
        PageResponse<GetCategoryResponse> response = categoryService.list(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> create(
            @Valid @RequestBody CreateCategoryRequest request) {
        CreateCategoryResponse response = categoryService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
