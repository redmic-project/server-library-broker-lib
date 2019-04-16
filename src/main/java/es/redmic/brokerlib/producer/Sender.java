package es.redmic.brokerlib.producer;

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
