package com.mathsena.springbootkafkatutorial.kafka;


import com.mathsena.springbootkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data) {
        try {
            LOGGER.info(String.format("Attempting to send message: %s", data));
            SendResult<String, User> result = kafkaTemplate.send(topicName, data).get();  // blocks until done
            LOGGER.info(String.format("Message sent successfully: %s", data));
        } catch (Exception ex) {
            LOGGER.error(String.format("Failed to send message: %s", data), ex);
        }
    }
}
