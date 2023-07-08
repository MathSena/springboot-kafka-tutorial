package com.mathsena.springbootkafkatutorial.kafka;

import com.mathsena.springbootkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics="${spring.kafka.topic-json.name}", groupId = "myGroup")
    public void consume(User user){
        try {
            LOGGER.info(String.format("json message received-> %s", user.toString())); // manually acknowledge the message
        } catch (Exception e) {
            LOGGER.error("Error processing message", e);
        }
    }

    }

