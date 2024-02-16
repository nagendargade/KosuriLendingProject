package com.example.kosuriTask.booleanConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.AttributedCharacterIterator;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "active" : "inactive";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "active".equalsIgnoreCase(dbData);
    }
}
