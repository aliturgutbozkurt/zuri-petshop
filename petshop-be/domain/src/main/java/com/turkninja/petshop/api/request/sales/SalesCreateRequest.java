package com.turkninja.petshop.api.request.sales;

import com.turkninja.petshop.enums.PaymentMethod;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesCreateRequest {
    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long userId;

    private boolean isPaid = false;

    @NotBlank
    private String orderNumber;

    @NotBlank
    @Email
    private String userEmail;
}
