package com.turkninja.petshop.v1;

import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderProductAddRequest;
import com.turkninja.petshop.api.request.order.OrderProductRemoveRequest;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderItemGetResponse;
import com.turkninja.petshop.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService service;

    @PostMapping("/products")
    public ResponseEntity<List<OrderItemGetResponse>> addProduct(@RequestBody @Valid OrderProductAddRequest request) {
        List<OrderItemGetResponse> response = service.addProduct(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/products")
    public ResponseEntity<List<OrderItemGetResponse>> removeProduct(@RequestBody @Valid OrderProductRemoveRequest request) {
        List<OrderItemGetResponse> response = service.removeProduct(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<OrderGetResponse> create(@RequestBody @Valid OrderCreateRequest request) {
        OrderGetResponse response = service.create(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderGetResponse> get(@PathVariable(value = "id") Long id) {
        OrderGetResponse response = service.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderGetResponse>> getByUserId(@RequestParam("userId") Long userId) {
        List<OrderGetResponse> response = service.getByUserId(userId);
        return ResponseEntity.ok().body(response);
    }
}