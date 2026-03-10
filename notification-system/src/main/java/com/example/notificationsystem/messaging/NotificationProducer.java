package com.example.notificationsystem.messaging;

import com.example.notificationsystem.model.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;
    private final String topic;

    public NotificationProducer(
            KafkaTemplate<String, NotificationMessage> kafkaTemplate,
            @Value("${app.kafka.notification-topic:notifications}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }
//    POST http://localhost:8080/api/notifications
//    	Content-Type: application/json
//
//    	{
//    	  "channels": ["EMAIL", "SMS", "PUSH"],
//    	  "recipient": "user@example.com",
//    	  "subject": "Test notification",
//    	  "body": "Hello from the async notification system",
//    	  "metadata": {
//    	    "foo": "bar"
//    	  }
//    	}

    public void send(NotificationMessage message) {
        kafkaTemplate.send(topic, message.getId(), message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish notification to Kafka, id={}", message.getId(), ex);
                    } else {
                        log.info("Notification published to Kafka, id={}, topic={}, partition={}, offset={}",
                                message.getId(),
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}

