package com.turkninja.petshop.api.request.user;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequest {

    @NotNull(message = "Email alanı boş olamaz")
    @Email(message = "Email formatı doğru olmalıdır")
    private String email;

    @NotNull
    private String password;
}
