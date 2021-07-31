package com.turkninja.petshop.exception;

import java.io.Serializable;
import java.util.Objects;

public class AppParameter {

    private String name;
    private Serializable value;

    public static AppParameter get(String name, Serializable value) {
        return new AppParameter(name, value);
    }

    private AppParameter(String name, Serializable value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Serializable getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppParameter param = (AppParameter) o;
        return Objects.equals(name, param.name) &&
                Objects.equals(value, param.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return '[' + name + '=' + value + ']';
    }
}
