package com.example.notificationsystem.service;

import com.example.notificationsystem.model.ChannelType;
import com.example.notificationsystem.model.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationDispatcher {

    private static final Logger log = LoggerFactory.getLogger(NotificationDispatcher.class);

    private final Map<ChannelType, NotificationChannelService> handlers = new EnumMap<>(ChannelType.class);

    public NotificationDispatcher(List<NotificationChannelService> services) {
        for (NotificationChannelService service : services) {
            handlers.put(service.getChannelType(), service);
        }
    }

    public void dispatch(NotificationMessage message) {
        if (message.getChannels() == null || message.getChannels().isEmpty()) {
            log.warn("Notification {} has no channels configured, skipping.", message.getId());
            return;
        }

        for (ChannelType channel : message.getChannels()) {
            NotificationChannelService handler = handlers.get(channel);
            if (handler == null) {
                log.warn("No handler configured for channel {}, notification id={}", channel, message.getId());
                continue;
            }
            handler.send(message);
        }
    }
}

