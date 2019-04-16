package es.redmic.brokerlib.listener;

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
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class SendListener implements ListenableFutureCallback<SendResult<String, ?>> {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void onSuccess(SendResult<String, ?> result) {
		// logger.info("Se ha enviado el mensaje con éxito al topic " +
		// result.getRecordMetadata().topic());
	}

	@Override
	public void onFailure(Throwable ex) {
		logger.error("Se ha producido un error al enviar el mensaje " + ex.getMessage());
	}
}
