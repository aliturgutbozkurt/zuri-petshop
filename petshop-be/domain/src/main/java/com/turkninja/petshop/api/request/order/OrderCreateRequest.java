package com.turkninja.petshop.api.request.order;

import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateRequest {
    @NotNull
    private String number;

    @NotNull
    private BigDecimal price;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private LocalDate deliveryDate;

    @NotNull
    private LocalTime deliveryTimeStart;

    @NotNull
    private LocalTime deliveryTimeEnd;

    private String note;

    private String promotionCode;

    @NotNull
    private Long userAddressId;
}
