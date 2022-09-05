package com.tejacodes.notification;

import com.tejacodes.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest notificationRequest)
    {
        log.info("Entering NotificationController.sendNotification()");
        log.info("NotificationRequest is: {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
        log.info("Exiting NotificationController.sendNotification()");
        return "Notification sent successfully!";
    }
}
