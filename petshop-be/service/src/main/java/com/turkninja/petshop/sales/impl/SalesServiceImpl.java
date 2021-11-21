package com.turkninja.petshop.sales.impl;

import com.turkninja.petshop.OrderRepository;
import com.turkninja.petshop.SalesRepository;
import com.turkninja.petshop.UserRepository;
import com.turkninja.petshop.api.request.sales.SalesCreateRequest;
import com.turkninja.petshop.api.response.sales.SalesGetResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.sales.SalesEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.SalesMapper;
import com.turkninja.petshop.sales.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesServiceImpl implements SalesService {
    private final SalesRepository salesRepository;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final SalesMapper salesMapper;

    @Override
    public SalesGetResponse create(SalesCreateRequest request) {
        log.info("Create a new sales by request:{}", request);

        OrderEntity order = orderRepository.findByNumber(request.getOrderNumber()).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("Order Number", request.getOrderNumber())));

        UserEntity user = userRepository.findByEmailAndActiveTrue(request.getUserEmail()).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("User Email", request.getUserEmail())));

        SalesEntity sales = SalesEntity.builder()
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .isPaid(request.isPaid())
                .order(order)
                .user(user)
                .build();

        salesRepository.save(sales);

        return salesMapper.entityToGetResponse(sales);
    }
}
