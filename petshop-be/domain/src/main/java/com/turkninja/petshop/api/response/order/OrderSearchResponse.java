package com.turkninja.petshop.api.response.order;

import com.turkninja.petshop.api.response.common.PaginatedResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderSearchResponse extends PaginatedResponse {
    private List<OrderGetResponse> orders;
}
