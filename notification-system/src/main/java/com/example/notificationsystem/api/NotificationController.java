package com.example.notificationsystem.api;

import com.example.notificationsystem.messaging.NotificationProducer;
import com.example.notificationsystem.model.NotificationMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationProducer producer;

    public NotificationController(NotificationProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> enqueueNotification(@Valid @RequestBody NotificationRequest request) {
        NotificationMessage message = new NotificationMessage();
        message.setChannels(request.getChannels());
        message.setRecipient(request.getRecipient());
        message.setSubject(request.getSubject());
        message.setBody(request.getBody());
        message.setMetadata(request.getMetadata());

        producer.send(message);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(message.getId());
    }
}

