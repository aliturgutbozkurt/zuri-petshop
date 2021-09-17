package com.turkninja.petshop.api.response.user;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAddressUpdateResponse {
    private Long id;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime revisedAt;

    private String name;
    private String province;
    private String district;
    private String neighborhood;
    private String street;
    private String building;
    private int floor;
    private String apartment;
    private String explanation;
}
