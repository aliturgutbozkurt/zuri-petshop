package com.turkninja.petshop.converters;


public class LowerCaseEnumConverter<T extends Enum> extends BaseConverter<T, String> {

    public LowerCaseEnumConverter(Class<T> sourceType) {
        super(sourceType, String.class);
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.name().toLowerCase();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return (T) Enum.valueOf(sourceType, dbData.toUpperCase());
    }
}
