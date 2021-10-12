package com.turkninja.petshop.api.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime revisedAt;

    private Integer number;
    private OrderState state;
    private BigDecimal price;
    private PaymentMethod paymentMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date deliveryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time deliveryTimeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time deliveryTimeEnd;

    private String note;
    private String dealerMessage;
    private String promotionCode;
    private List<OrderItemGetResponse> orderItems;
    private UserAddressEntity userAddress;
}
