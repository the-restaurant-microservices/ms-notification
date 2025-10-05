package com.example.msnotification.consumer;

import com.example.msnotification.service.MailService;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RestaurantEventsListener {

    static Logger log = LoggerFactory.getLogger(RestaurantEventsListener.class);
    MailService mailService;

    public RestaurantEventsListener(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(
            topics = "${app.topics.restaurant-events}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onMessage(
            String payload,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.info("📥 Received from {} [p={}, off={}]: {}", topic, partition, offset, payload);

        mailService.sendNotificationEmail(
                "ilahaguliyeva23@GMAIL.COM",
                "Yeni restoran yaradıldı 🚀",
                "Kafka-dan gələn məlumat: " + payload
        );
    }
}
