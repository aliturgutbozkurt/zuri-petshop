package com.turkninja.petshop.api.request.user;


import com.turkninja.petshop.value.FullName;
import com.turkninja.petshop.value.Phone;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserSignupRequest {

    @Valid
    private FullName fullName;

    @NotNull(message = "Email alanı boş olamaz")
    @Email(message = "Email formatı doğru olmalıdır")
    private String email;

    @NotNull(message = "Şifre alanı boş olamaz")
    @Size(min = 6, max = 15, message = "Şifre en az 6 en çok 15 karakter olmalıdır")
    private String password;

    @Valid
    private Phone phone;
}
