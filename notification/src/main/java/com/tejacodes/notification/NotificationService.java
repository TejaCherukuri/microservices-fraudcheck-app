package com.tejacodes.notification;

import com.tejacodes.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest)
    {
        log.info("Entering NotificationService.sendNotification()");

        Notification notification = Notification.builder()
                                    .toCustomerId(notificationRequest.toCustomerId())
                                    .toCustomerEmail(notificationRequest.toCustomerEmail())
                                    .sender("NotificationMicroservice")
                                    .message(notificationRequest.message())
                                    .sentAt(LocalDateTime.now())
                                    .build();
        log.info("Sending Notification {}", notification);
        notificationRepository.save(notification);

        log.info("Exiting NotificationService.sendNotification()");

    }
}
