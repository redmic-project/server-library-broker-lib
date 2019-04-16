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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CustomRelationDeserializerTest {

	@Mock
	private JsonParser jp;

	ObjectMapper mapper = new ObjectMapper();
	static TestDTO test;
	static TestDTO testFromId;

	@BeforeClass
	public static void setUp() {
		test = new TestDTO();
		test.setId("item-1");
		test.setName("test");

		testFromId = new TestDTO();
		testFromId.setId("item-1");

	}

	@Test
	public void testDeserializeById() throws ParseException, JsonParseException, JsonProcessingException, IOException {
		TestDTO item = deserialize("item-1");
		assertEquals(item.getId(), testFromId.getId());
	}

	@Test
	public void testDeserializeByObject()
			throws ParseException, JsonParseException, JsonProcessingException, IOException {
		TestDTO item = deserialize(test);
		assertEquals(item.getId(), testFromId.getId());
	}

	private TestDTO deserialize(String id) throws IOException, JsonParseException, JsonProcessingException {
		TestObject testObject = mapper.readValue("{\"test\":\"item-1\"}", TestObject.class);

		return testObject.getTest();
	}

	private TestDTO deserialize(TestDTO item) throws IOException, JsonParseException, JsonProcessingException {

		TestObject testObject = mapper.readValue("{\"test\":" + mapper.writeValueAsString(item) + "}",
				TestObject.class);

		return testObject.getTest();
	}

	private static class TestObject {

		@JsonDeserialize(using = CustomRelationDeserializer.class)
		private final TestDTO test = null;

		public TestDTO getTest() {
			return test;
		}
	}
}
