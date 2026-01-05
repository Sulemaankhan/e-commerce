package com.email.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.email.dto.NotificationDto;
import com.email.service.Notification;

import lombok.extern.slf4j.Slf4j;

@Service("SMS")
@Slf4j
public class Sms implements Notification{

	@Override
	public String notifyUser(String message, String subject,String recipient) {

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			log.info("Sms sender");
			// Setting up necessary details
			mailMessage.setFrom("");
			//System.out.println("Sender :" + sender);
			mailMessage.setTo(recipient);
			mailMessage.setText(message);
			mailMessage.setSubject(subject);

			// Sending the mail
			//javaMailSender.send(mailMessage);
			return "Successfully Notify To..." + recipient;
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}	

}
