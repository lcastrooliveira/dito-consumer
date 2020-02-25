package dev.lcastrooliveira.ditoconsumer.configs;

import dtos.EventDocumentDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, EventDocumentDTO> eventsDTOConsumerFactory() {
        final Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        final JsonDeserializer<EventDocumentDTO> eventDocumentDTOJsonDeserializer = new JsonDeserializer<>(EventDocumentDTO.class);
        eventDocumentDTOJsonDeserializer.addTrustedPackages("dtos");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), eventDocumentDTOJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventDocumentDTO> eventsDTOContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, EventDocumentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventsDTOConsumerFactory());
        return factory;
    }
}
