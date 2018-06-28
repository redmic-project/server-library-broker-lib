package es.redmic.brokerlib.alert;

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
