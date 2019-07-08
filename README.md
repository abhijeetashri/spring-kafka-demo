# Microservice Kafka Demo
Basic implementation for Kafka producer & consumer.

# Technologies:
Spring Boot
Spring Kafka
Apache Kafka
Zookeeper

# How To Run:
1. Run kafka-producer.
2. Send a POST request to kafka-producer (eg., http://localhost:8090/notifications/NOTIFICATION) with a JSON body, with any test data.
3. Run kafka-consumer and the data consumed from the queue is displayed on the console.
