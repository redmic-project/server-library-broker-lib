package es.redmic.brokerlib.serde.hashmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.brokerlib.avro.serde.hashmap.HashMapDeserializer;
import es.redmic.brokerlib.avro.serde.hashmap.HashMapSerializer;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class HashMapSerializerDeserializerTest {

	private final SchemaRegistryClient schemaRegistry;

	protected KafkaAvroSerializer avroSerializer;

	protected KafkaAvroDeserializer avroDeserializer;

	protected ObjectMapper objectMapper = new ObjectMapper();

	private final Serde<HashMap<String, Object>> inner;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMapSerializerDeserializerTest() {

		Properties defaultConfig = new Properties();
		defaultConfig.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "bogus");
		defaultConfig.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");

		schemaRegistry = new MockSchemaRegistryClient();

		avroSerializer = new KafkaAvroSerializer(schemaRegistry, new HashMap(defaultConfig));

		avroDeserializer = new KafkaAvroDeserializer(schemaRegistry, new HashMap(defaultConfig));

		inner = Serdes.serdeFrom(new HashMapSerializer<>(avroSerializer), new HashMapDeserializer<>(avroDeserializer));
	}

	@Test
	public void MessageWrapperSerializeAndDeserialize_IsSuccessful_IfSchemaAndDataIsCorrect()
			throws JsonProcessingException {

		// @formatter:off

		String key = "dsd",
				topic = "msg.t";
		
		// @formatter:on

		Map<String, Object> content = new HashMap<String, Object>();
		content.put(key, "sads");

		byte[] dtoBytes = inner.serializer().serialize(topic, (HashMap<String, Object>) content);

		HashMap<String, Object> result = inner.deserializer().deserialize(topic, dtoBytes);

		assertTrue("El objeto obtenido debe ser una instancia de HashMap", HashMap.class.isInstance(result));

		assertEquals(result, content);
		assertEquals(result.get(key), content.get(key));
	}
}
