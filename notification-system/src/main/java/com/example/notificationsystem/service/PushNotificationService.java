package com.example.notificationsystem.service;

import com.example.notificationsystem.model.ChannelType;
import com.example.notificationsystem.model.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService implements NotificationChannelService {

    private static final Logger log = LoggerFactory.getLogger(PushNotificationService.class);

    @Override
    public ChannelType getChannelType() {
        return ChannelType.PUSH;
    }

    @Override
    public void send(NotificationMessage message) {
        // Integrate with FCM, APNs, or another push provider here.
        log.info("Simulating PUSH send, id={}, to={}, subject={}, body={}",
                message.getId(), message.getRecipient(), message.getSubject(), message.getBody());
    }
}

