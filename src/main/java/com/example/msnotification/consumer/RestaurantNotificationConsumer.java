package com.example.msnotification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RestaurantNotificationConsumer {

    @KafkaListener(topics = "restaurant.created", groupId = "notification-group")
    public void consumeMessage(String message) {
        System.out.println("📩 Notification alındı: " + message);
    }
}