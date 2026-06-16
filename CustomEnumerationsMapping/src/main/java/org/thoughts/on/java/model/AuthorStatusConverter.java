package org.thoughts.on.java.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class AuthorStatusConverter implements AttributeConverter<AuthorStatus, String> {

	Logger log = LoggerFactory.getLogger(AuthorStatusConverter.class);
	
	@Override
	public String convertToDatabaseColumn(AuthorStatus status) {
		switch (status) {
		case NOT_PUBLISHED:
			logDbConversion(status, "N");
			return "N";
			
		case PUBLISHED:
			logDbConversion(status, "P");
			return "P";
			
		case SELF_PUBLISHED:
			logDbConversion(status, "S");
			return "S";

		default:
			throw new IllegalArgumentException("AuthorStatus ["+status+"] not supported.");
		}
	}

	@Override
	public AuthorStatus convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "N":
			logEntityConversion(AuthorStatus.NOT_PUBLISHED, "N");
			return AuthorStatus.NOT_PUBLISHED;
			
		case "P":
			logEntityConversion(AuthorStatus.PUBLISHED, "P");
			return AuthorStatus.PUBLISHED;
			
		case "S":
			logEntityConversion(AuthorStatus.SELF_PUBLISHED, "S");
			return AuthorStatus.SELF_PUBLISHED;
			
		default:
			throw new IllegalArgumentException("AuthorStatus ["+dbData+"] not supported.");
		}
	}

	private void logDbConversion(AuthorStatus status, String dbData) {
		log.info("Convert AuthorStatus enum ["+status+"] to ["+dbData+"].");
	}
	
	private void logEntityConversion(AuthorStatus status, String dbData) {
		log.info("Convert DB value ["+dbData+"] to AuthorStatus enum ["+status+"].");
	}
}
