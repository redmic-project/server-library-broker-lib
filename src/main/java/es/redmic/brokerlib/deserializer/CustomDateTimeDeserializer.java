package es.redmic.brokerlib.deserializer;

/*-
 * #%L
 * broker-lib
 * %%
 * Copyright (C) 2019 REDMIC Project / Server
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
