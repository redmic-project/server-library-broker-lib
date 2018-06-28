package es.redmic.brokerlib.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomRelationDeserializer extends CustomObjectBaseDeserializer {

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

		String id = jp.getValueAsString();
		// Si devuelve null es que es un objeto.
		if (id != null) { // Se crea el objeto relación con el id obtenido
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue("{\"id\":\"" + id + "\"}", targetClass);
		} else // Se realiza la acción por defecto
			return jp.readValueAs(targetClass);
	}
}