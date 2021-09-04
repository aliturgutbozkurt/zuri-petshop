package com.turkninja.petshop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.turkninja.petshop.converters.AbstractEnumConverter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OrderState implements PersistableEnum<Integer> {
    NEW(1, "New"), PACKAGED(2, "Packaged"), SHIPPED(3, "Shipped"), DELIVERED(4, "Delivered"), CANCELLED(5, "Cancelled");

    private OrderState(int code, String name) {
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

    private static final Map<Integer, OrderState> valueEnumMap = Arrays.stream(OrderState.values()).collect(Collectors.toMap(e -> e.code, Function.identity()));

    @JsonCreator
    public static OrderState decode(final int code) {
        return Optional.ofNullable(valueEnumMap.get(code)).orElseThrow(IllegalArgumentException::new);
    }

    public static class Converter extends AbstractEnumConverter<OrderState, Integer> {
        public Converter() {
            super(OrderState.class);
        }
    }
}
