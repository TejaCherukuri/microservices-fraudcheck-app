package com.tejacodes.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification", path="/api/v1/notification")
public interface NotificationClient {

    @PostMapping
    String sendNotification(NotificationRequest notificationRequest);
}
