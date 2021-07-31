package com.turkninja.petshop.util;

public class ToStringBuilder {

    private final StringBuilder sb = new StringBuilder();
    private final Class<?> type;

    public ToStringBuilder() {
        this(null);
    }

    public ToStringBuilder(Class<?> type) {
        this.type = type;
    }

    public void add(String name, Object value) {
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(name).append('=').append(String.valueOf(value));
    }

    private String getClassName() {
        return type != null ? type.getSimpleName() : "";
    }

    @Override
    public String toString() {
        return getClassName() + '[' + sb.toString() + ']';
    }
}
