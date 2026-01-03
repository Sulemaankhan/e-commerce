package com.email.service;

import com.email.dto.EmailDetails;

public interface EmailService {

	public String sendSimpleMail(EmailDetails details);

	public String sendMailWithAttachment(EmailDetails details);

}
