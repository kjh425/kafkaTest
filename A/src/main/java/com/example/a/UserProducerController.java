package com.example.a;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class UserProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public String sendUsers() {
        for (int i = 1; i <= 50; i++) {
            String name = "user_" + i;
            kafkaTemplate.send("user-topic", name);
            System.out.println("보낸다!!!: " + name);
        }
        return "Sent 50 users!";
    }
}
