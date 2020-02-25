package dev.lcastrooliveira.ditoconsumer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumerService {

    @KafkaListener(topics = "events", groupId = "spring-events-consumer-group")
    public void registerEvent(String event) {
        log.info("Received event: {}", event);
    }

}
