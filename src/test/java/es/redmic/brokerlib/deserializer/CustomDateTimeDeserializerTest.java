package es.redmic.brokerlib.deserializer;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.redmic.exception.databinding.DateTimeDeserializerException;

public class CustomDateTimeDeserializerTest {

	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.WRAP_EXCEPTIONS, false);

	static DateTime daylightSavingTimeSummerExpected;
	static String daylightSavingTimeSummerSource;

	static DateTime daylightSavingTimeWinterExpected;
	static String daylightSavingTimeWinterSource;

	@BeforeClass
	public static void setUp() {

		daylightSavingTimeSummerExpected = new DateTime(2017, 3, 26, 0, 0, 0, 0, DateTimeZone.UTC);
		daylightSavingTimeSummerSource = "2017-03-26T01:00:00.000+01:00";

		daylightSavingTimeWinterExpected = new DateTime(2017, 10, 29, 1, 0, 0, 0, DateTimeZone.UTC);
		daylightSavingTimeWinterSource = "2017-10-29T01:00:00.000+00:00";
	}

	@Test
	public void checkDaylightSavingTimeSummerDeserializer_IsCorrect_IfFormatIsCorrect() throws Exception {

		DateTime item = deserialize(daylightSavingTimeSummerSource);
		assertEquals(item, daylightSavingTimeSummerExpected);
	}

	@Test
	public void checkDaylightSavingTimeWinterDeserializer_IsCorrect_IfFormatIsCorrect() throws Exception {

		DateTime item = deserialize(daylightSavingTimeWinterSource);
		assertEquals(item, daylightSavingTimeWinterExpected);
	}

	@Test(expected = DateTimeDeserializerException.class)
	public void checkDaylightSavingTimeWinterDeserializer_ThrowException_IfFormatIsWrong() throws Exception {

		deserialize("26-03-2017T01:00:00.000+01:00");
	}

	private DateTime deserialize(String dateTime) throws Exception {

		TestObject testObject = mapper.readValue("{\"date\":" + mapper.writeValueAsString(dateTime) + "}",
				TestObject.class);

		return testObject.getDate();
	}

	private static class TestObject {

		@JsonDeserialize(using = CustomDateTimeDeserializer.class)
		private final DateTime date = null;

		public DateTime getDate() {
			return date;
		}
	}
}