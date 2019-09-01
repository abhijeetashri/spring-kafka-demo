package com.spring.kafka.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka
@DirtiesContext
public class NotificationServiceTest {

	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;

	@Autowired
	private NotificationServiceImpl service;

	private Consumer<String, String> consumer;

	static {
		System.setProperty(EmbeddedKafkaBroker.BROKER_LIST_PROPERTY, "app.kafka.bootstrap-servers");
		System.setProperty("app.kafka.client.id", "test");
	}

	@Before
	public void setup() {
		Map<String, Object> consumerConfigs = new HashMap<>(
				KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
		consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumer = new DefaultKafkaConsumerFactory<>(consumerConfigs, new StringDeserializer(),
				new StringDeserializer()).createConsumer();
		consumer.subscribe(Collections.singleton("test_topic"));
	}

	@Test
	public void testingKafkaProducer() {
		service.createNotificationForTopic("test_topic", "my-test-value");
		ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "test_topic");
		Assertions.assertThat(singleRecord).isNotNull();
		Assertions.assertThat(singleRecord.value()).isEqualTo("my-test-value");
	}

	@After
	public void shutdown() {
		consumer.close();
	}
}
