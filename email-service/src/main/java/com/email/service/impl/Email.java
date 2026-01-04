package com.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.dto.NotificationDto;
import com.email.service.Notification;

@Service("EMAIL")
@Primary
public class Email implements Notification{
	
	
	private final JavaMailSender javaMailSender;
	
	public Email(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String notifyUser(NotificationDto details) {
		System.out.println("Email");
		
		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			System.out.println("Email Sender :" + sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully to..." + details.getRecipient();
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}


}
