package com.turkninja.petshop.api.request.order;

import com.turkninja.petshop.enums.OrderState;
import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderSearchRequest {
    private Integer number;
    private OrderState state;
    private PaymentMethod paymentMethod;
    private Date deliveryDate;
}
