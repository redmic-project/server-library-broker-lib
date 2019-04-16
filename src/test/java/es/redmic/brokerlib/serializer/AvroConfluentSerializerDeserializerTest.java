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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.redmic.brokerlib.avro.common.MessageWrapper;
import es.redmic.brokerlib.utils.AvroBaseTest;

public class AvroConfluentSerializerDeserializerTest extends AvroBaseTest {

	@Test
	public void MessageWrapperSerializeAndDeserialize_IsSuccessful_IfSchemaAndDataIsCorrect()
			throws JsonProcessingException {

		MessageWrapper messageWrapper = new MessageWrapper();

		Map<String, String> content = new HashMap<String, String>();
		content.put("dsd", "sads");
		messageWrapper.setContent(ByteBuffer.wrap(objectMapper.writeValueAsBytes(content)));
		messageWrapper.setUserId("13");

		Object result = serializerAndDeserializer(messageWrapper);

		assertTrue("El objeto obtenido debe ser una instancia de messagewrapper",
				MessageWrapper.class.isInstance(result));

		assertEquals(result, messageWrapper);
	}
}
