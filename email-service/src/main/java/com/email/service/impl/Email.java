package com.email.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.service.Notification;

import lombok.extern.slf4j.Slf4j;

@Service("EMAIL")
@Primary
@Slf4j
public class Email implements Notification {

	private final JavaMailSender javaMailSender;

	public Email(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String notifyUser(String recipient, String message, String subject) {
		log.info("Email impl called");

		try {
			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			System.out.println("Email Sender :" + sender);
			mailMessage.setTo(recipient);
			mailMessage.setText(message);
			mailMessage.setSubject(subject);

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Successfully Nofity To..." + recipient;
		}
		// Catch block to handle the exceptions
		catch (Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}

}
