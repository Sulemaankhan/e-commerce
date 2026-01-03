package com.email.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.email.dto.NotificationDto;
import com.email.service.Notification;

@Service("SMS")
public class Sms implements Notification{

	@Override
	public String notifyUser(NotificationDto details) {

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			System.out.println("Sms sender");
			// Setting up necessary details
			//mailMessage.setFrom(sender);
			//System.out.println("Sender :" + sender);
			mailMessage.setTo(details.getNumber());
			mailMessage.setText(details.getMsgBody());

			// Sending the mail
			//javaMailSender.send(mailMessage);
			return "Mail Sent Successfully to..." + details.getRecipient();
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}	

}
