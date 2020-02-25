package dev.lcastrooliveira.ditoconsumer.controllers;

import dev.lcastrooliveira.ditoconsumer.documents.EventDocument;
import dev.lcastrooliveira.ditoconsumer.services.EventConsumerService;
import dtos.EventDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/events")
public class EventConsumerController {

    public final EventConsumerService eventConsumerService;

    @Autowired
    public EventConsumerController(EventConsumerService eventConsumerService) {
        this.eventConsumerService = eventConsumerService;
    }

    @GetMapping
    public Page<EventDocumentDTO> findByEvent(@RequestParam @Min(2) String eventName, Pageable pageable) {
        return eventConsumerService.findByEvent(eventName, pageable);
    }

}
