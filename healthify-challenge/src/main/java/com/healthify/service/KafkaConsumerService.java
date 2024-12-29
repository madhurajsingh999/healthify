package com.healthify.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "challenge-topic", groupId = "challenge-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        // Process the message
    }
}