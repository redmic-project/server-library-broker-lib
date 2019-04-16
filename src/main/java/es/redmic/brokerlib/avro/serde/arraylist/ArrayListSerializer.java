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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class ArrayListSerializer<T> implements Serializer<ArrayList<T>> {

	KafkaAvroSerializer serializer;

	// Default constructor needed by Kafka
	public ArrayListSerializer() {
	}

	public ArrayListSerializer(KafkaAvroSerializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		this.serializer.configure(configs, isKey);
	}

	@Override
	public byte[] serialize(String topic, ArrayList<T> queue) {
		final int size = queue.size();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final DataOutputStream dos = new DataOutputStream(baos);
		final Iterator<T> iterator = queue.iterator();
		try {
			dos.writeInt(size);
			while (iterator.hasNext()) {
				final byte[] bytes = serializer.serialize(topic, iterator.next());
				dos.writeInt(bytes.length);
				dos.write(bytes);
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
