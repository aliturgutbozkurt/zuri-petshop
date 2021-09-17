package com.turkninja.petshop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.turkninja.petshop.converters.AbstractEnumConverter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentMethod implements PersistableEnum<Integer> {
    CASH(1, "Cash"), CREDIT_CARD(2, "Credit Card"), MONEY_TRANSFER(3, "Money Transfer");

    private PaymentMethod(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, PaymentMethod> valueEnumMap = Arrays.stream(PaymentMethod.values()).collect(Collectors.toMap(e -> e.code, Function.identity()));

    @JsonCreator
    public static PaymentMethod decode(final int code) {
        return Optional.ofNullable(valueEnumMap.get(code)).orElseThrow(IllegalArgumentException::new);
    }

    public static class Converter extends AbstractEnumConverter<PaymentMethod, Integer> {
        public Converter() {
            super(PaymentMethod.class);
        }
    }
}