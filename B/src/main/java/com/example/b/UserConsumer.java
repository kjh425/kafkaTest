package com.example.b;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @KafkaListener(topics = "user-topic", groupId = "user-group-b")
    public void consume(String message) {
        System.out.println("[B] Received: " + message);
    }
}
