package com.tejacodes.notification.rabbitmq;

import com.tejacodes.clients.notification.NotificationRequest;
import com.tejacodes.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consume(NotificationRequest notificationRequest)
    {
        log.info("Consumed {} from Notification Queue", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
