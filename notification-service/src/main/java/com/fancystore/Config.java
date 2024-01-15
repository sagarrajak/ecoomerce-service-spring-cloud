package com.fancystore;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Config {

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacesEvent event) {
        // send out email notification
        log.info("Notification for order - {}", event.getOrderNumber());
    }
}
