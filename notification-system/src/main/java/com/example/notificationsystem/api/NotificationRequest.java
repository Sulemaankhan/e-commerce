package com.example.notificationsystem.api;

import com.example.notificationsystem.model.ChannelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.Set;

public class NotificationRequest {

    @NotEmpty
    private Set<ChannelType> channels;

    @NotBlank
    private String recipient;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    private Map<String, Object> metadata;

    public Set<ChannelType> getChannels() {
        return channels;
    }

    public void setChannels(Set<ChannelType> channels) {
        this.channels = channels;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

