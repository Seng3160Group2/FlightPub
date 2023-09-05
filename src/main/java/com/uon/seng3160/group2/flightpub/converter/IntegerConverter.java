package com.uon.seng3160.group2.flightpub.converter;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true) // Apply this converter automatically to all entities
public class IntegerConverter implements AttributeConverter<Integer, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Integer attribute) {
        return attribute; // No conversion needed for database storage
    }

    @Override
    public Integer convertToEntityAttribute(Integer dbData) {
        return dbData != null ? dbData : 0; // Convert null to 0 for Java entity
    }
}
