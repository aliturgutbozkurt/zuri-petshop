package com.turkninja.petshop.order;

import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderProductAddRequest;
import com.turkninja.petshop.api.request.order.OrderProductRemoveRequest;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderItemGetResponse;

import java.util.List;

public interface OrderService {
    OrderGetResponse create(OrderCreateRequest request);

    OrderGetResponse getById(Long id);

    List<OrderGetResponse> getByUserId(Long userId);

    List<OrderItemGetResponse> addProduct(OrderProductAddRequest request);

    List<OrderItemGetResponse> removeProduct(OrderProductRemoveRequest request);
}
