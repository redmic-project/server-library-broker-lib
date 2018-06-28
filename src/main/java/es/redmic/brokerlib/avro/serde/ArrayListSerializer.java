package es.redmic.brokerlib.avro.serde;

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
		// do nothing
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
		// do nothing
	}
}
