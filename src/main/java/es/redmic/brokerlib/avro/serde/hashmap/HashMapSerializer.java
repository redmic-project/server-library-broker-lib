package es.redmic.brokerlib.avro.serde.hashmap;

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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class HashMapSerializer<K, V> implements Serializer<HashMap<K, V>> {

	KafkaAvroSerializer serializer;

	// Default constructor needed by Kafka
	public HashMapSerializer() {
	}

	public HashMapSerializer(KafkaAvroSerializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		this.serializer.configure(configs, isKey);
	}

	@Override
	public byte[] serialize(String topic, HashMap<K, V> queue) {

		final int size = queue.size();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(size);
			for (Map.Entry<K, V> entry : queue.entrySet()) {

				final byte[] bytesKey = entry.getKey().toString().getBytes();
				dos.writeInt(bytesKey.length);
				dos.write(bytesKey);

				final byte[] bytesValue = serializer.serialize(topic, entry.getValue());
				dos.writeInt(bytesValue.length);
				dos.write(bytesValue);
			}
		} catch (IOException e) {
			throw new RuntimeException("Unable to serialize ArrayList", e);
		}
		return baos.toByteArray();
	}

	@Override
	public void close() {
		this.serializer.close();
	}
}
