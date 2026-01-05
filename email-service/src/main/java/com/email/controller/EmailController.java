package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.impl.NotificationFactory;
import com.email.service.impl.NotificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notify")
@AllArgsConstructor
public class EmailController {

	private final NotificationService notificationService;
	private final NotificationFactory notificationFactory;

//	public EmailController(EmailService emailService,NotificationFactory notificationFactory) {
//		this.notificationFactory = notificationFactory;
//		this.emailService=emailService;
//	}

	// Sending a simple Email
//	@PostMapping("/sendMail")
//	public ResponseEntity<?> sendNotification(@RequestBody NotificationDto details) {
//		String status = emailService.sendSimpleMail(details);
//
//		return new ResponseEntity<String>(status, HttpStatus.CREATED);
//	}

	// Sending a simple Email
	@PostMapping("/{type}")
	public ResponseEntity<?> sendNotification(@PathVariable String type, @RequestParam String recipient, @RequestParam String message,@RequestParam String subject) {
		//http://localhost:6060/email-service/notify/email?recipient=acbcloud@gmail.com&message=Hello&subject=registration success
		// Create the appropriate Notification object using the factory
		try {
		String status=notificationService.send(type,recipient,message,subject);
		//return "Notification queued via " + type;
		return new ResponseEntity<String>(status+", By :"+type, HttpStatus.CREATED);
		}catch (Exception e){
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
