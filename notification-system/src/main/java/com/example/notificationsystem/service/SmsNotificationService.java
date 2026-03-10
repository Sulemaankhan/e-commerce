package com.example.notificationsystem.service;

import com.example.notificationsystem.model.ChannelType;
import com.example.notificationsystem.model.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationChannelService {

    private static final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);

    @Override
    public ChannelType getChannelType() {
        return ChannelType.SMS;
    }

    @Override
    public void send(NotificationMessage message) {
        // Integrate with your SMS provider (Twilio, etc.) here.
        log.info("Simulating SMS send, id={}, to={}, body={}",
                message.getId(), message.getRecipient(), message.getBody());
    }
}

