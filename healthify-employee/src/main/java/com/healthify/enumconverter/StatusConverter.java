package com.healthify.enumconverter;

import com.healthify.entity.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        log.info("STATUS : {}", dbData);
        return Status.valueOf(dbData);
    }

}