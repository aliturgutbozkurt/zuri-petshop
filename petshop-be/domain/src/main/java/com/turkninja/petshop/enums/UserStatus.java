package com.turkninja.petshop.enums;


import com.turkninja.petshop.converters.LowerCaseEnumConverter;

/**
 * Created by aliturgut.bozkurt
 * Date: 12/22/18.
 */
public enum UserStatus {
    ACTIVE,
    PASSIVE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @javax.persistence.Converter
    public static class Converter extends LowerCaseEnumConverter<UserStatus> {
        public Converter() {
            super(UserStatus.class);
        }
    }
}
