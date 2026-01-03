package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.dto.NotificationDto;
import com.email.service.EmailService;
import com.email.service.Notification;
import com.email.service.impl.NotificationFactory;

@RestController
@RequestMapping("/emails")
public class EmailController {

	private final EmailService emailService;
	private final NotificationFactory notificationFactory;

	public EmailController(EmailService emailService,NotificationFactory notificationFactory) {
		this.notificationFactory = notificationFactory;
		this.emailService=emailService;
	}

	// Sending a simple Email
	@PostMapping("/sendMail")
	public ResponseEntity<?> sendNotification(@RequestBody NotificationDto details) {
		String status = emailService.sendSimpleMail(details);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}

	// Sending a simple Email
	@PostMapping("/notification/{type}")
	public ResponseEntity<?> sendNotification(@PathVariable String type, @RequestBody NotificationDto details) {

		// Create the appropriate Notification object using the factory
		try {
			Notification notification = notificationFactory.getMethod(type);
			String status = notification.notifyUser(details);
			return new ResponseEntity<String>(status, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

//	// Sending email with attachment
//	@PostMapping("/sendMailWithAttachment")
//	public ResponseEntity<?> sendMailWithAttachment(@RequestBody EmailNotificationDto details) {
//		String status = emailService.sendMailWithAttachment(details);
//
//		return new ResponseEntity<String>(status, HttpStatus.CREATED);
//	}

}
