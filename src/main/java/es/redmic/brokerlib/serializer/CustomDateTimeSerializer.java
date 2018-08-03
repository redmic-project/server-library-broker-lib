package es.redmic.brokerlib.serializer;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.redmic.exception.databinding.DateTimeSerializerException;

public class CustomDateTimeSerializer extends JsonSerializer<DateTime> {

	@Override
	public void serialize(DateTime value, JsonGenerator gen, SerializerProvider arg2) {

		String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

		try {
			gen.writeString(value.withZone(DateTimeZone.UTC).toString(format));
		} catch (IOException e) {
			throw new DateTimeSerializerException(format, value.toString(), e);
		}
	}
}
