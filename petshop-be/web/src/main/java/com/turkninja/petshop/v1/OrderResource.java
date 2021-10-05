package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderSearchRequest;
import com.turkninja.petshop.api.request.order.OrderUpdateRequest;
import com.turkninja.petshop.api.response.order.OrderCreateResponse;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderSearchResponse;
import com.turkninja.petshop.api.response.order.OrderUpdateResponse;
import com.turkninja.petshop.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderResource {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderCreateResponse> create(@RequestBody @Valid OrderCreateRequest request) {
        OrderCreateResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderUpdateResponse> update(@PathVariable(value = "id") Long id,
                                                      @RequestBody @Valid OrderUpdateRequest request) {
        OrderUpdateResponse response = service.update(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderGetResponse> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderGetResponse> get(@PathVariable(value = "id") Long id) {
        OrderGetResponse response = service.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<OrderSearchResponse> search(
            @RequestBody @Valid OrderSearchRequest request,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) @SortDefault.SortDefaults({
                    @SortDefault(sort = "number", direction = Sort.Direction.DESC)}) Pageable pageable) {
        OrderSearchResponse response = service.search(request, pageable);
        return ResponseEntity.ok().body(response);
    }
}