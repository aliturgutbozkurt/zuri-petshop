package com.turkninja.petshop.order;

import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderSearchRequest;
import com.turkninja.petshop.api.request.order.OrderUpdateRequest;
import com.turkninja.petshop.api.response.order.OrderCreateResponse;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderSearchResponse;
import com.turkninja.petshop.api.response.order.OrderUpdateResponse;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderCreateResponse create(OrderCreateRequest request);

    OrderUpdateResponse update(Long id, OrderUpdateRequest request);

    void delete(Long id);

    OrderGetResponse getById(Long id);

    OrderGetResponse getByNumber(int number);

    OrderSearchResponse search(OrderSearchRequest searchRequest, Pageable pageable);
}
