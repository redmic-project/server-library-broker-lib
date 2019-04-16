package es.redmic.brokerlib.utils;

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
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.brokerlib.avro.common.MessageWrapper;

public abstract class MessageWrapperUtils {

	static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> getMessageFromMessageWrapper(MessageWrapper payload) {

		if (payload.getContent() == null || payload.getContent().array().length < 1)
			return new HashMap<>();

		Map<String, Object> message = null;
		try {
			message = objectMapper.readValue(payload.getContent().array(), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}

	public static ByteBuffer getContent(Object dto) throws JsonProcessingException {

		return ByteBuffer.wrap(objectMapper.writeValueAsBytes(dto));
	}
}
