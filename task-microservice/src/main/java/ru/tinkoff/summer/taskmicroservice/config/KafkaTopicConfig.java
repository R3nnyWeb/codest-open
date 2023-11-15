package ru.tinkoff.summer.taskmicroservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


import java.util.HashMap;
import java.util.Map;

import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public NewTopic attemptTopic() {
        logger.error("Atttempts topics = {}", ATTEMPT_TOPIC_PARTITIONS);
        return TopicBuilder.name(ATTEMPT_TOPIC_NAME).partitions(ATTEMPT_TOPIC_PARTITIONS).build();
    }

    @Bean
    public NewTopic resultTopic() {
        logger.error("Result topics = {}", RESULT_TOPIC_PARTITIONS);
        return TopicBuilder.name(RESULT_TOPIC_NAME).partitions(RESULT_TOPIC_PARTITIONS).build();
    }
}
