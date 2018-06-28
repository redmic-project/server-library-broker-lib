package es.redmic.brokerlib.utils;

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
