package es.redmic.brokerlib.avro.serde.arraylist;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class ArrayListSerde<T> implements Serde<ArrayList<T>> {

	private final Serde<ArrayList<T>> inner;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayListSerde(String schemaRegistryUrl) {

		Properties defaultConfig = new Properties();
		defaultConfig.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		defaultConfig.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");

		SchemaRegistryClient schemaRegistry = new CachedSchemaRegistryClient(schemaRegistryUrl, 1000);

		KafkaAvroDeserializer deserializer = new KafkaAvroDeserializer(schemaRegistry, new HashMap(defaultConfig));

		KafkaAvroSerializer serializer = new KafkaAvroSerializer(schemaRegistry, new HashMap(defaultConfig));

		inner = Serdes.serdeFrom(new ArrayListSerializer<>(serializer), new ArrayListDeserializer<>(deserializer));
	}

	@Override
	public Serializer<ArrayList<T>> serializer() {
		return inner.serializer();
	}

	@Override
	public Deserializer<ArrayList<T>> deserializer() {
		return inner.deserializer();
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		inner.serializer().configure(configs, isKey);
		inner.deserializer().configure(configs, isKey);
	}

	@Override
	public void close() {
		inner.serializer().close();
		inner.deserializer().close();
	}
}
