package com.example.notificationsystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NotificationMessage {

    private String id;

    @NotNull
    private Set<ChannelType> channels;

    @NotBlank
    private String recipient;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    // Optional metadata (for templates, provider-specific data, etc.)
    private Map<String, Object> metadata;

    private Instant createdAt;

    public NotificationMessage() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

