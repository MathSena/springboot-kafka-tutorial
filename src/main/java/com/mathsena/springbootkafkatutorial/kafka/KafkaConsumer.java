package com.mathsena.springbootkafkatutorial.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics="${spring.kafka.topic.name}", groupId = "myGroup")
    public void consumeMessage(String message){
        log.info("Message received: {}", message);
    }
}