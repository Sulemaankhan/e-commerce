package com.email.service;

import com.email.dto.NotificationDto;

public interface Notification {
	public String notifyUser(NotificationDto details);
}
