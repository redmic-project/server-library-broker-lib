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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class ArrayListDeserializer<T> implements Deserializer<ArrayList<T>> {

	KafkaAvroDeserializer deserializer;

	// Default constructor needed by Kafka
	public ArrayListDeserializer() {
	}

	public ArrayListDeserializer(KafkaAvroDeserializer deserializer) {
		this.deserializer = deserializer;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		this.deserializer.configure(configs, isKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> deserialize(String topic, byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}

		final ArrayList<T> arrayList = new ArrayList<>();
		final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));

		try {
			final int records = dataInputStream.readInt();
			for (int i = 0; i < records; i++) {
				final byte[] valueBytes = new byte[dataInputStream.readInt()];
				dataInputStream.read(valueBytes);
				arrayList.add((T) deserializer.deserialize(topic, valueBytes));
			}
		} catch (IOException e) {
			throw new RuntimeException("Unable to deserialize ArrayList", e);
		}

		return arrayList;
	}

	@Override
	public void close() {
		this.deserializer.close();
	}
}
