package es.redmic.brokerlib.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import es.redmic.brokerlib.avro.common.MessageWrapper;
import es.redmic.brokerlib.listener.SendListener;

@Component
public class Sender {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private KafkaTemplate<String, MessageWrapper> kafkaTemplate;

	public Sender() {
		logger.info("Creando sender");
	}

	public void send(String topic, MessageWrapper payload) {

		logger.info("sending payload='{}' to topic='{}'", payload, topic);

		String key = payload.getUserId() != null ? payload.getUserId() : "anonymous";

		ListenableFuture<SendResult<String, MessageWrapper>> future = kafkaTemplate.send(topic, key, payload);

		future.addCallback(new SendListener());
	}
}
