package com.email.dto;

import java.util.List;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.InternetAddress;

public class EmailDetails {
	
	 // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
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
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "EmailDto [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + ", attachment="
				+ attachment + "]";
	}
    
    
	
	

}
