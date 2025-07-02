package com.example.c;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @KafkaListener(topics = "user-topic", groupId = "user-group-c")
    public void consume(String message) {
        System.out.println("[C] Received: " + message);
    }
}
