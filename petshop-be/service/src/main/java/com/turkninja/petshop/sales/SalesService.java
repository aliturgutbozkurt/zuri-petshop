package com.turkninja.petshop.sales;

import com.turkninja.petshop.api.request.sales.SalesCreateRequest;
import com.turkninja.petshop.api.response.sales.SalesGetResponse;

public interface SalesService {
    SalesGetResponse create(SalesCreateRequest request);
}
