package com.turkninja.petshop.enums;


import com.turkninja.petshop.converters.LowerCaseEnumConverter;

/**
 * Created by aliturgut.bozkurt
 * Date: 12/22/18.
 */
public enum Gender {
    FEMALE,
    MALE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @javax.persistence.Converter
    public static class Converter extends LowerCaseEnumConverter<Gender> {
        public Converter() {
            super(Gender.class);
        }
    }
}
