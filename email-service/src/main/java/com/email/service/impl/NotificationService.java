package com.email.service.impl;

import org.springframework.stereotype.Service;

import com.email.enums.NotificationType;
import com.email.service.Notification;

@Service
public class NotificationService {

	private final NotificationFactory factory;

	public NotificationService(NotificationFactory factory) {
		this.factory = factory;
	}

	public String send(String typeKey, String recipient,String message, String subject) {
		//return runtime factory method
		Notification notification = factory.getNotification(typeKey);
		//called Impl obj based on the type
		return notification.notifyUser(recipient,message, subject);
	}

//	public String send(NotificationType type, String message, String subject, String recipient) {
//		return send(type.key(), message, subject, recipient);
//	}
}
