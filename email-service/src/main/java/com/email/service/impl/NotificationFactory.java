package com.email.service.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.email.enums.NotificationType;
import com.email.service.Notification;

@Component
public class NotificationFactory {

	private final Map<String, Notification> notificationMap;

	public NotificationFactory(Map<String, Notification> notificationMap) {
		this.notificationMap = notificationMap;
	}

	public Notification getNotification(String typeKey) {
		Notification notification = notificationMap.get(typeKey.toUpperCase());
		if (notification == null) {
			throw new IllegalArgumentException("Unknown notification type: " + typeKey);
		}
		return notification;
	}

	public Notification getNotification(NotificationType type) {
		return getNotification(type.key());
	}
}
