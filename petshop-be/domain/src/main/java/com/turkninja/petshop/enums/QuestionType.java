package com.turkninja.petshop.enums;

import com.turkninja.petshop.converters.LowerCaseEnumConverter;

public enum QuestionType {
    PRODUCT,
    COMMON;

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
