package com.healthify.enumconverter;

import com.healthify.entity.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        log.info("GENDER : {}", dbData);
        return Gender.valueOf(dbData);
    }

}