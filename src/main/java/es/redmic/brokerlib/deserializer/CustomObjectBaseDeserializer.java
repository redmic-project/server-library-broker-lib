package es.redmic.brokerlib.deserializer;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

public abstract class CustomObjectBaseDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

	protected Class<?> targetClass;

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {

		final JavaType type;
		if (property != null)
			type = property.getType(); // -> beanProperty is null when the StringConvertible type is a root value
		else {
			type = ctxt.getContextualType();
		}
		// ==== Get raw Class from type info =====
		targetClass = type.getRawClass();
		return this;
	}
}
