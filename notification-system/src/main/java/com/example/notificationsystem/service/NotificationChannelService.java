package com.example.notificationsystem.service;

import com.example.notificationsystem.model.NotificationMessage;
import com.example.notificationsystem.model.ChannelType;

public interface NotificationChannelService {

    ChannelType getChannelType();

    void send(NotificationMessage message);
}

