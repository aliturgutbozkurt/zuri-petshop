package com.turkninja.petshop.api.request.order;

import com.turkninja.petshop.entity.order.OrderItemEntity;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateRequest {
    @NotNull
    private BigDecimal price;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Date deliveryDate;

    @NotNull
    private Time deliveryTimeStart;

    @NotNull
    private Time deliveryTimeEnd;

    private String note;

    private String promotionCode;

    private List<OrderItemEntity> orderItems;

    private UserAddressEntity userAddress;
}
