package com.mathsena.springbootkafkatutorial.controller;

import com.mathsena.springbootkafkatutorial.kafka.JsonKafkaProducer;
import com.mathsena.springbootkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMessageController.class);

    private final JsonKafkaProducer kafkaProducer;

    @Autowired
    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        try {
            kafkaProducer.sendMessage(user);
            LOGGER.info("Json message sent to Kafka topic");
            return ResponseEntity.ok("Json message sent to Kafka topic");
        } catch(Exception e) {
            LOGGER.error("Failed to send message to Kafka topic", e);
            return new ResponseEntity<>("Failed to send message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}