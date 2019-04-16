package es.redmic.brokerlib.serializer;

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

import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class CustomDateTimeSerializerTest {

	static ObjectMapper mapper = new ObjectMapper();

	static String dateTimeExpected;
	static DateTime dateTimeSource;

	@BeforeClass
	public static void setUp() {

		mapper.registerModule(new JodaModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		dateTimeExpected = "\"2015-03-17T01:00:00.000+00:00\"";
		dateTimeSource = new DateTime(2015, 3, 17, 1, 0, 0, 0, DateTimeZone.UTC);
	}

	@Test
	public void checkDateTimeSerializer_IsCorrect_IfIsEqualToExpected() throws Exception {

		assertTrue(serialize(dateTimeSource).contains(dateTimeExpected));
	}

	private String serialize(DateTime dateTime) throws Exception {

		TestObject testObject = new TestObject();
		testObject.setDate(dateTime);

		return mapper.writeValueAsString(testObject);
	}

	private class TestObject {

		private DateTime date;

		public void setDate(DateTime date) {
			this.date = date;
		}

		@JsonSerialize(using = CustomDateTimeSerializer.class)
		public DateTime getDate() {
			return date;
		}
	}

}
