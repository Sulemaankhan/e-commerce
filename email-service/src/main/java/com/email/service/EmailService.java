package com.email.service;

import com.email.dto.NotificationDto;

public interface EmailService {

	public String sendSimpleMail(NotificationDto details);

	///public String sendMailWithAttachment(EmailNotificationDto details);

}
