package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.dto.EmailDetails;
import com.email.service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public ResponseEntity<?> sendMail(@RequestBody EmailDetails details) {
		String status = emailService.sendSimpleMail(details);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public ResponseEntity<?> sendMailWithAttachment(@RequestBody EmailDetails details) {
		String status = emailService.sendMailWithAttachment(details);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}

}
