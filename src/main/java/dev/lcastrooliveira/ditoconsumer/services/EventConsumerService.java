package dev.lcastrooliveira.ditoconsumer.services;

import dev.lcastrooliveira.ditoconsumer.documents.EventDocument;
import dev.lcastrooliveira.ditoconsumer.repositories.EventRepository;
import dtos.EventDocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumerService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventConsumerService(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public Page<EventDocumentDTO> findByEvent(String eventName, Pageable pageable) {
        return eventRepository.findByEventContaining(eventName, pageable)
                              .map(event -> modelMapper.map(event, EventDocumentDTO.class));
    }

    @KafkaListener(topics = "events", groupId = "spring-events-consumer-group", containerFactory = "eventsDTOContainerFactory")
    public void registerEvent(EventDocumentDTO eventDTO) {
        log.info("Received event: {}", eventDTO.toString());
        final EventDocument savedEvent = eventRepository.save(modelMapper.map(eventDTO, EventDocument.class));
        log.info("Persisted event {} on Elasticsearch. Id: {}", savedEvent.getEvent(), savedEvent.getId());
    }
}
