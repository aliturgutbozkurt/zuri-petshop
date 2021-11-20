package com.turkninja.petshop.api.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turkninja.petshop.api.response.user.UserAddressGetResponse;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderGetResponse {
    private String number;

    private OrderState state;
    private BigDecimal price;
    private PaymentMethod paymentMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime deliveryTimeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime deliveryTimeEnd;

    private String note;
    private String dealerMessage;
    private String promotionCode;
    private List<OrderItemGetResponse> orderItems;
    private UserAddressGetResponse userAddress;
}
