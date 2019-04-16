package es.redmic.brokerlib.serde.arraylist;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.brokerlib.avro.serde.arraylist.ArrayListDeserializer;
import es.redmic.brokerlib.avro.serde.arraylist.ArrayListSerializer;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class ArrayListSerializerDeserializerTest {

	private final SchemaRegistryClient schemaRegistry;

	protected KafkaAvroSerializer avroSerializer;

	protected KafkaAvroDeserializer avroDeserializer;

	protected ObjectMapper objectMapper = new ObjectMapper();

	private final Serde<ArrayList<Object>> inner;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayListSerializerDeserializerTest() {

		Properties defaultConfig = new Properties();
		defaultConfig.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "bogus");
		defaultConfig.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");

		schemaRegistry = new MockSchemaRegistryClient();

		avroSerializer = new KafkaAvroSerializer(schemaRegistry, new HashMap(defaultConfig));

		avroDeserializer = new KafkaAvroDeserializer(schemaRegistry, new HashMap(defaultConfig));

		inner = Serdes.serdeFrom(new ArrayListSerializer<>(avroSerializer),
				new ArrayListDeserializer<>(avroDeserializer));
	}

	@Test
	public void MessageWrapperSerializeAndDeserialize_IsSuccessful_IfSchemaAndDataIsCorrect()
			throws JsonProcessingException {

		// @formatter:off

		String topic = "msg.t";
		
		Object value = "ff";
		
		// @formatter:on

		ArrayList<Object> content = new ArrayList<Object>();
		content.add(value);

		byte[] dtoBytes = inner.serializer().serialize(topic, content);

		ArrayList<Object> result = inner.deserializer().deserialize(topic, dtoBytes);

		assertTrue("El objeto obtenido debe ser una instancia de ArrayList", ArrayList.class.isInstance(result));

		assertEquals(result, content);
		assertEquals(result.get(0), content.get(0));
	}
}
