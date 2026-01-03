package com.email.dto;

import java.util.List;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.InternetAddress;

public class NotificationDto {
	
	 // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String number;
    
    
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "EmailNotificationDto [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject
				+ ", number=" + number + "]";
	}
    
    
	
	

}
