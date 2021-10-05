package com.turkninja.petshop.api.response.order;

import com.turkninja.petshop.entity.order.OrderItemEntity;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderGetResponse {
    private Long id;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime revisedAt;

    private Integer number;
    private OrderState state;
    private BigDecimal price;
    private PaymentMethod paymentMethod;
    private Date deliveryDate;
    private Time deliveryTimeStart;
    private Time deliveryTimeEnd;
    private String note;
    private String dealerMessage;
    private String promotionCode;
    private List<OrderItemEntity> orderItems;
    private UserAddressEntity userAddress;
}
