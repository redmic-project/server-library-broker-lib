package es.redmic.brokerlib.alert;

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

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.redmic.brokerlib.utils.AvroBaseTest;

public class AlertServiceTest extends AvroBaseTest {

	@Test
	public void MessageWrapperSerializeAndDeserialize_IsSuccessful_IfSchemaAndDataIsCorrect()
			throws JsonProcessingException {

		Message message = new Message("info@redmic.es", "test", "mensaje de test", AlertType.ERROR.name());

		Object result = serializerAndDeserializer(message);

		assertTrue("El objeto obtenido debe ser una instancia de messagewrapper", Message.class.isInstance(result));

		assertEquals(result, message);
	}
}
