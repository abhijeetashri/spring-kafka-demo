package com.spring.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

	@KafkaListener(topics = "NOTIFICATION")
	public void notificationListener(String body) {
		System.out.println(body);
	}
}
