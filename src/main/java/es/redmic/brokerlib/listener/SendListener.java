package es.redmic.brokerlib.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class SendListener implements ListenableFutureCallback<SendResult<String, ?>> {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void onSuccess(SendResult<String, ?> result) {
		logger.info("Se ha enviado el mensaje con éxito al topic " + result.getRecordMetadata().topic());
	}

	@Override
	public void onFailure(Throwable ex) {
		logger.error("Se ha producido un error al enviar el mensaje " + ex.getMessage());
	}
}
