package es.redmic.brokerlib.serializer;

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