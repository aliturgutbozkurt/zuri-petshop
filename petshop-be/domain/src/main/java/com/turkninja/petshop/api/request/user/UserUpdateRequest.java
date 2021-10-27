package com.turkninja.petshop.api.request.user;

import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Request object holding updatable fields to update a user entity.
 *
 * @author mstar
 * @version 1.0
 * @since 1.0, 2021-10-14
 */
@Data
public class UserUpdateRequest {

    @Nullable
    @Size(min = 2, max = 20, message = "İsim en az 2 en çok 20 karakter olmalıdır")
    private String name;

    @Nullable
    @Size(min = 2, max = 25, message = "Soyad en az 2 en çok 25 karakter olmalıdır")
    private String lastName;

    @Nullable
    @Size(min = 10, max = 10, message = "Telefon numarasını doğru giriniz")
    private String mobilePhone;

    @Nullable
    @Pattern(regexp = ("MALE|FEMALE"), message = "MALE ya da FEMALE değerlerinden biri girilmelidir.")
    private String gender;
}
