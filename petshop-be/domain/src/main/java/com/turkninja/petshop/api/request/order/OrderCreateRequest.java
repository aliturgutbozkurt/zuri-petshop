package com.turkninja.petshop.api.request.order;

import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateRequest {
    @NotNull
    private Long id;

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

    @NotNull
    private Long userAddressId;
}
