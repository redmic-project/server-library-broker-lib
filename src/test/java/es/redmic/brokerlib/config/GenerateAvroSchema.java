package es.redmic.brokerlib.config;

import static org.junit.Assert.assertEquals;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;

import es.redmic.brokerlib.avro.common.MessageWrapper;

public class GenerateAvroSchema {

	@Test
	public void generateSchema() {

		MessageWrapper msg = new MessageWrapper();

		Schema schema = ReflectData.get().getSchema(MessageWrapper.class);

		assertEquals(msg.getSchema(), schema);
	}

}
