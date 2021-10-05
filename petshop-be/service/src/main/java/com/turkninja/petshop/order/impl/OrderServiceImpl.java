package com.turkninja.petshop.order.impl;

import com.turkninja.petshop.OrderRepository;
import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderSearchRequest;
import com.turkninja.petshop.api.request.order.OrderUpdateRequest;
import com.turkninja.petshop.api.response.order.OrderCreateResponse;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderSearchResponse;
import com.turkninja.petshop.api.response.order.OrderUpdateResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.OrderMapper;
import com.turkninja.petshop.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderCreateResponse create(@Valid OrderCreateRequest request) {
        log.info("Create a new order by request:{}", request);

        OrderEntity orderEntity = orderMapper.createRequestToEntity(request);

        orderRepository.save(orderEntity);

        return orderMapper.entityToCreateResponse(orderEntity);
    }

    @Override
    public OrderUpdateResponse update(Long id, @Valid OrderUpdateRequest request) {
        log.info("Update the order by id:{} and request:{}", id, request);

        OrderEntity order = findById(id);
        order.setState(request.getState());
        order.setPrice(request.getPrice());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setDeliveryTimeStart(request.getDeliveryTimeStart());
        order.setDeliveryTimeEnd(request.getDeliveryTimeEnd());
        order.setNote(request.getNote());
        order.setOrderItems(request.getOrderItems());
        order.setUserAddress(request.getUserAddress());

        orderRepository.save(order);

        return orderMapper.entityToUpdateResponse(order);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete the order by id:{}", id);

        OrderEntity orderEntity = orderRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("id", id)));
        orderEntity.setActive(false);

        orderRepository.save(orderEntity);
    }

    @Override
    public OrderGetResponse getById(Long id) {
        log.info("Get the order by id:{}", id);

        OrderEntity orderEntity = orderRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("id", id)));

        return orderMapper.entityToGetResponse(orderEntity);
    }

    @Override
    public OrderGetResponse getByNumber(int number) {
        log.info("Get the order by number:{}", number);

        OrderEntity orderEntity = orderRepository.findByNumberAndActiveTrue(number).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("number", number)));

        return orderMapper.entityToGetResponse(orderEntity);
    }

    @Override
    public OrderSearchResponse search(OrderSearchRequest searchRequest, Pageable pageable) {
        log.info("Search all orders by searchRequest:{} and pageable:{}", searchRequest, pageable);

        Stream<OrderEntity> orders = orderRepository.search(searchRequest, pageable);
        List<OrderGetResponse> result = orders.map(orderMapper::entityToGetResponse).collect(Collectors.toList());

        OrderSearchResponse response = new OrderSearchResponse();
        response.setOrders(result);
        response.setSize(pageable.getPageSize());
        response.setPage(pageable.getPageNumber());
        response.setTotalCount(count(searchRequest));

        return response;
    }

    private OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("id", id)));
    }

    private Long count(OrderSearchRequest searchRequest){
        Example<OrderEntity> orderExample = createOrderExample(searchRequest);
        return orderRepository.count(orderExample);
    }

    private Example<OrderEntity> createOrderExample(OrderSearchRequest searchRequest) {
        OrderEntity order = new OrderEntity();
        order.setNumber(searchRequest.getNumber());
        order.setState(searchRequest.getState());
        order.setPaymentMethod(searchRequest.getPaymentMethod());
        order.setDeliveryDate(searchRequest.getDeliveryDate());
        return Example.of(order);
    }
}
