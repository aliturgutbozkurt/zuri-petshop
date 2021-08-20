package com.turkninja.petshop.api.request.product;

import lombok.Data;

import java.util.Objects;

/**
 * @author ali turgut bozkurt
 * Created at 8/20/2021
 */

@Data
public class CreateProductImageRequest {

    private final String url;

    private final int orderNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductImageRequest that = (CreateProductImageRequest) o;
        return getOrderNumber() == that.getOrderNumber() && getUrl().equals(that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getOrderNumber());
    }
}
