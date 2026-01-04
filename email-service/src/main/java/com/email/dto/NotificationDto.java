package com.email.dto;

import java.util.List;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.InternetAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationDto {
	
	 // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String number;
}
