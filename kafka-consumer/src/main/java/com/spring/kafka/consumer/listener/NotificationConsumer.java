package com.spring.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

	@KafkaListener(topics = "NOTIFICATION")
	@SendTo(value = "FORWARD")
	public String notificationListener(String body) {
		System.out.println(body);
		return "The message " + body + " has been processed";
	}

	@KafkaListener(topics = "FORWARD")
	public void receiveResponse(String body) {
		System.out.println(body);
	}
}
