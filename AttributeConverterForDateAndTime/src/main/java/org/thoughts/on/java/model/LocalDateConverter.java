package org.thoughts.on.java.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	Logger log = LoggerFactory.getLogger(LocalDateConverter.class.getSimpleName());

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		log.info("Convert to java.sql.Date");
		return Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		log.info("Convert to java.time.LocalDate");
		return dbData.toLocalDate();
	}
}
