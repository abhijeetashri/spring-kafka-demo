package com.spring.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public Long createNotificationForTopic(String topicName, String eventBody) throws KafkaException {
		LOGGER.info("Topic name: {}", topicName);
		try {
			SendResult<String, String> result = this.kafkaTemplate.send(topicName, eventBody).get();
			if (result == null) {
				LOGGER.error("Result is null");
				throw new KafkaException("Result is null");
			}
			return result.getRecordMetadata().offset();
		} catch (Exception e) {
			LOGGER.error("Error sending Kafka message", e);
			throw new KafkaException("Error in KafkaProducer", e);
		}
	}
}
