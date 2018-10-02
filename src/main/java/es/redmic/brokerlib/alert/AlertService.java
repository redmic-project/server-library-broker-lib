package es.redmic.brokerlib.alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

	private static Logger logger = LogManager.getLogger();

	@Value("${broker.topic.alert}")
	private String ALERT_TOPIC;

	@Value("${alert.email}")
	private String ALERT_EMAIL;

	@Value("${spring.profiles.active}")
	private String PROFILE_ACTIVE;

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	public void errorAlert(String subject, String message) {

		String subjectDefault = "[ERROR][" + PROFILE_ACTIVE + "] ";
		send(new Message(ALERT_EMAIL, subjectDefault + subject, message, AlertType.ERROR.name()));
	}

	private void send(Message message) {

		logger.info("sending alert='{}' to topic='{}'", message, ALERT_TOPIC);

		kafkaTemplate.send(ALERT_TOPIC, String.valueOf(DateTime.now().getMillis()), message);
	}
}
