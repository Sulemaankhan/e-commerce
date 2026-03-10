package com.example.notificationsystem.messaging;

import com.example.notificationsystem.model.NotificationMessage;
import com.example.notificationsystem.service.NotificationDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificationDispatcher dispatcher;

    public NotificationConsumer(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @KafkaListener(
            topics = "${app.kafka.notification-topic:notifications}",
            groupId = "notification-consumers",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void onMessage(@Payload NotificationMessage message) {
        log.info("Received notification from Kafka, id={}", message.getId());
        dispatcher.dispatch(message);
    }
}

