package es.redmic.brokerlib.serializer;

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
