package com.turkninja.petshop.converters;

import javax.persistence.AttributeConverter;

/**
 * Created by aliturgut.bozkurt
 * Date: 12/19/18.
 */
public abstract class BaseConverter<T, U> implements AttributeConverter<T, U> {
    protected final Class<T> sourceType;
    protected final Class<U> targetType;

    public BaseConverter(Class<T> sourceType, Class<U> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }
}

