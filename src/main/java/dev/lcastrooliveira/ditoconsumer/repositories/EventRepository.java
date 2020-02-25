package dev.lcastrooliveira.ditoconsumer.repositories;

import dev.lcastrooliveira.ditoconsumer.documents.EventDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ElasticsearchRepository<EventDocument, String> {
    Page<EventDocument> findByEventContaining(String eventName, Pageable pageable);
}
