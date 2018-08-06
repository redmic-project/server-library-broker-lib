package es.redmic.brokerlib.avro.serde.hashmap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class HashMapSerializer<K, V> implements Serializer<HashMap<K, V>> {

	KafkaAvroSerializer serializer;

	StringSerializer stringSerializer;

	// Default constructor needed by Kafka
	public HashMapSerializer() {
	}

	public HashMapSerializer(KafkaAvroSerializer serializer) {
		this.serializer = serializer;
		this.stringSerializer = new StringSerializer();
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

				final byte[] bytesKey = stringSerializer.serialize(topic, entry.getKey().toString());
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
