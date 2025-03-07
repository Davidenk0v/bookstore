package com.bookstore.users.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = {"bookstore"}, groupId = "my-group-id")
    public void listener(String message){
        //Implement your logic here with websocket
        LOGGER.info("Message received: " + message);
    }
}
