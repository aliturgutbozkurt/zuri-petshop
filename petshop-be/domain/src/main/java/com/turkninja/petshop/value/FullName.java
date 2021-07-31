package com.turkninja.petshop.value;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class FullName {

    @Column(name = "name")
    @NotNull(message = "İsim alanı boş olamaz")
    @Size(min = 2, max = 20, message = "İsim en az 2 en çok 20 karakter olmalıdır")
    private String name;

    @Column(name = "last_name")
    @NotNull(message = "Soyisim alanı boş olamaz")
    @Size(min = 2, max = 25, message = "İsim en az 2 en çok 25 karakter olmalıdır")
    private String lastName;
}
