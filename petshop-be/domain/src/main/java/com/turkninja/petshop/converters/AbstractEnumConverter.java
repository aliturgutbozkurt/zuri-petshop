package com.turkninja.petshop.converters;

import com.turkninja.petshop.enums.PersistableEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public abstract class AbstractEnumConverter<T extends Enum<T> & PersistableEnum<E>, E> implements AttributeConverter<T, E> {
    private final Class<T> clazz;

    public AbstractEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
    }
}