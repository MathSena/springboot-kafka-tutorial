package com.mathsena.springbootkafkatutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private static final String KAFKA_TOPIC_NAME = "${spring.kafka.topic.name}";
    private static final String KAFKA_TOPIC_JSON_NAME = "${spring.kafka.topic-json.name}";

    private final String topicName;
    private final String topicNameJson;

    public KafkaTopicConfig(@Value(KAFKA_TOPIC_NAME) String topicName,
                            @Value(KAFKA_TOPIC_JSON_NAME) String topicNameJson) {
        this.topicName = topicName;
        this.topicNameJson = topicNameJson;
    }

    @Bean
    public NewTopic mathsenaTopic() {
        return TopicBuilder.name(topicName)
                .partitions(3)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic mathsenaJsonTopic() {
        return TopicBuilder.name(topicNameJson)
                .partitions(3)
                .replicas(2)
                .build();
    }
}
