package es.redmic.brokerlib.deserializer;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import es.redmic.exception.databinding.DateTimeDeserializerException;

public class CustomDateTimeDeserializer extends JsonDeserializer<DateTime> {

	final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

	@Override
	public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) {

		DateTimeFormatter patternFormat = DateTimeFormat.forPattern(pattern);
		String dateTime;

		try {
			dateTime = jp.getText();
		} catch (IOException e) {
			throw new DateTimeDeserializerException(pattern, null, e);
		}

		try {
			return patternFormat.parseDateTime(dateTime).toDateTime(DateTimeZone.UTC);
		} catch (Exception e) {

			throw new DateTimeDeserializerException(pattern, dateTime, e);
		}

	}
}