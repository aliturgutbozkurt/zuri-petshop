package com.turkninja.petshop.api.response.sales;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turkninja.petshop.entity.order.OrderEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesGetResponse {
    private Long id;

    private PaymentMethod paymentMethod;

    private BigDecimal amount;

    private boolean isPaid;

    private UserEntity user;

    private OrderEntity order;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;
}