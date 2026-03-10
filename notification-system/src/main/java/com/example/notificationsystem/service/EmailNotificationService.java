package com.example.notificationsystem.service;

import com.example.notificationsystem.model.ChannelType;
import com.example.notificationsystem.model.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationChannelService {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void send(NotificationMessage message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getRecipient());
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getBody());
            mailSender.send(mailMessage);
            log.info("Email sent, id={}, to={}", message.getId(), message.getRecipient());
        } catch (Exception ex) {
            log.error("Failed to send email notification, id={}", message.getId(), ex);
        }
    }
}

