package com.turkninja.petshop.order.impl;

import com.turkninja.petshop.*;
import com.turkninja.petshop.api.request.order.OrderCreateRequest;
import com.turkninja.petshop.api.request.order.OrderProductAddRequest;
import com.turkninja.petshop.api.request.order.OrderProductRemoveRequest;
import com.turkninja.petshop.api.response.order.OrderGetResponse;
import com.turkninja.petshop.api.response.order.OrderItemGetResponse;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.order.OrderItemEntity;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.OrderItemMapper;
import com.turkninja.petshop.mapper.OrderMapper;
import com.turkninja.petshop.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderGetResponse getById(Long id) {
        log.info("Get the order by id:{}", id);

        OrderEntity entity = orderRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("id", id)));

        return orderMapper.entityToGetResponse(entity);
    }

    @Override
    public List<OrderGetResponse> getByUserId(Long userId) {
        log.info("Get all orders by userId:{}", userId);

        List<OrderEntity> entities = orderRepository.findByUserIdAndActiveTrue(userId);

        return entities.stream().map(orderMapper::entityToGetResponse).collect(Collectors.toList());
    }

    @Override
    public OrderGetResponse create(OrderCreateRequest request) {
        log.info("Create a new order by request:{}", request);

        OrderEntity order = getDraftOrderById(request.getId());
        UserAddressEntity userAddress = userAddressRepository.findByIdAndActiveTrue(request.getUserAddressId()).get();
        order.setPrice(request.getPrice());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setDeliveryTimeStart(request.getDeliveryTimeStart());
        order.setDeliveryTimeEnd(request.getDeliveryTimeEnd());
        order.setNote(request.getNote());
        order.setPromotionCode(request.getPromotionCode());
        order.setUserAddress(userAddress);
        order.setState(OrderState.NEW);

        orderRepository.save(order);

        return orderMapper.entityToGetResponse(order);
    }

    @Override
    public List<OrderItemGetResponse> addProduct(OrderProductAddRequest request) {
        log.info("Add a product by request:{}", request);

        OrderEntity draftOrder = getDraftOrder(request.getUserId());

        addProduct(draftOrder, request);

        return getAllOrderItems(draftOrder);
    }

    @Override
    public List<OrderItemGetResponse> removeProduct(OrderProductRemoveRequest request) {
        log.info("Remove a new product by request:{}", request);

        OrderEntity draftOrder = getDraftOrder(request.getUserId());

        removeProduct(draftOrder, request);

        return getAllOrderItems(draftOrder);
    }

    private OrderEntity getDraftOrder(Long userId) {
        Optional<OrderEntity> order = orderRepository.findByIdAndStateAndActiveTrue(userId, OrderState.DRAFT);
        if (order.isPresent()) {
            return order.get();
        } else {
            return createDraftOrder(userId);
        }
    }

    private OrderEntity createDraftOrder(Long userId) {
        UserEntity user = userRepository.findByIdAndActiveTrue(userId).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("userId", userId)));

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setState(OrderState.DRAFT);

        return orderRepository.save(order);
    }

    private OrderEntity getDraftOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("orderId", id)));

        if (order.getState() != OrderState.DRAFT) {
            throw new ApplicationException(AppMessage.METHOD_ARGUMENT_NOT_VALID, AppParameter.get("state", order.getState()));
        }

        return order;
    }

    private void addProduct(OrderEntity draftOrder, OrderProductAddRequest request) {
        Optional<OrderItemEntity> orderItem = orderItemRepository.findByOrderIdAndProductIdAndActiveTrue(draftOrder.getId(), request.getProductId());

        if (orderItem.isPresent()) {
            OrderItemEntity orderItemEntity = orderItem.get();
            orderItemEntity.setCount(orderItemEntity.getCount() + 1);
            orderItemRepository.save(orderItemEntity);
        } else {
            ProductEntity product = productRepository.findByIdAndActiveTrue(request.getProductId()).orElseThrow(() ->
                    new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("productId", request.getProductId())));

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(draftOrder);
            orderItemEntity.setProduct(product);
            orderItemEntity.setCount(1);

            orderItemRepository.save(orderItemEntity);
        }
    }

    private List<OrderItemGetResponse> getAllOrderItems(OrderEntity draftOrder) {
        List<OrderItemEntity> orderItems = orderItemRepository.findByOrderIdAndActiveTrue(draftOrder.getId());
        return orderItems.stream().map(orderItemMapper::entityToGetResponse).collect(Collectors.toList());
    }

    private void removeProduct(OrderEntity draftOrder, OrderProductRemoveRequest request) {
        Optional<OrderItemEntity> orderItem = orderItemRepository.findByOrderIdAndProductIdAndActiveTrue(draftOrder.getId(), request.getProductId());

        if (orderItem.isEmpty()) {
            throw new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("Order item is not found!", null));
        }

        OrderItemEntity orderItemEntity = orderItem.get();
        orderItemEntity.setCount(orderItemEntity.getCount() - 1);

        if (orderItemEntity.getCount() > 0) {
            orderItemRepository.save(orderItemEntity);
        } else {
            orderItemRepository.delete(orderItemEntity);
        }
    }
}
