package com.turkninja.petshop.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone {
    @Column(name = "mobile_phone")
    @NotNull(message = "Telefon alanı boş olamaz")
    @Size(min = 10, max = 10, message = "Telefon numarasını doğru giriniz")
    private String mobilePhone;
}
