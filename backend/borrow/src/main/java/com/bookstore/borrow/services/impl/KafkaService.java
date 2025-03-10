package com.bookstore.borrow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private static final String TOPIC = "bookstore";
    private static final String SERVICE = "borrow-service: ";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        kafkaTemplate.send(TOPIC, SERVICE + message);
    }
}
