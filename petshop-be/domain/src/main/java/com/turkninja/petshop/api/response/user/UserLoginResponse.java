package com.turkninja.petshop.api.response.user;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;
    private String refreshToken;
    private String tokenType;
}
