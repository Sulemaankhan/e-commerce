package com.email.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.email.service.Notification;
@Component
public class NotificationFactory {

    private final Map<String, Notification> notifications;

    public NotificationFactory(Map<String, Notification> notifications) {
        this.notifications = notifications;
    }

    public Notification getMethod(String type) {
        Notification notification = notifications.get(type.toUpperCase());
        System.out.println(type.toUpperCase()+ ": Servcie Object is created...");
        
        if (notification == null) {
            throw new IllegalArgumentException("Unknown notification type: " + type);
        }
        return notification;
    }
}
