package es.redmic.brokerlib.alert;

import javax.annotation.PostConstruct;

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

	@Value("${info.vessels-commands.name}")
	private String MICROSERVICE_NAME;

	private String subjectDefault;

	@PostConstruct
	private void AlertServicePostConstruct() {

		subjectDefault = "[" + MICROSERVICE_NAME + "][" + PROFILE_ACTIVE + "]";
	}

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	public void infoAlert(String subject, String message) {

		alert(subject, message, AlertType.INFO.name());
	}

	public void warnAlert(String subject, String message) {

		alert(subject, message, AlertType.WARN.name());
	}

	public void errorAlert(String subject, String message) {

		alert(subject, message, AlertType.ERROR.name());
	}

	public void alert(String subject, String message, String type) {

		send(new Message(ALERT_EMAIL, subjectDefault + "[" + type + "] " + subject, message, type));
	}

	private void send(Message message) {

		logger.info("sending alert='{}' to topic='{}'", message, ALERT_TOPIC);

		kafkaTemplate.send(ALERT_TOPIC, String.valueOf(DateTime.now().getMillis()), message);
	}
}
