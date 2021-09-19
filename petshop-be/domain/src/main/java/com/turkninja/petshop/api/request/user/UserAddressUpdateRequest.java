package com.turkninja.petshop.api.request.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAddressUpdateRequest {
    @NotNull
    private String name;

    @NotNull
    private String province;

    @NotNull
    private String district;

    private String neighborhood;

    private String street;

    private String building;

    private int floor;

    private String apartment;

    private String explanation;
}