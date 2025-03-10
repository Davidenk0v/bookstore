package com.bookstore.notifications.kafka;

import com.bookstore.notifications.websocket.SimpleWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class KafkaConsumerListener {


    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);
    @Autowired
    SimpleWebSocketHandler simpleWebSocketHandler = new SimpleWebSocketHandler();

    @KafkaListener(topics = {"bookstore"}, groupId = "my-group-id")
    public void listener(String message) throws IOException {
        //Implement your logic here with websocket
        LOGGER.info("Message received from  " + message);
        simpleWebSocketHandler.sendMessageToUser(message);
    }
}
