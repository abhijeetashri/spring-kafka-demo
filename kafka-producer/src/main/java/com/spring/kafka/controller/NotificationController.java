package com.spring.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kafka.service.NotificationServiceImpl;

@RestController
@RequestMapping(path = "/notifications")
public class NotificationController {

	@Autowired
	private NotificationServiceImpl notificationService;

	@PostMapping(path = "/{topicName}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createNotification(@PathVariable("topicName") final String topicName, @RequestBody String eventBody)
			throws KafkaException {
		this.notificationService.createNotificationForTopic(topicName, eventBody);
	}
}
