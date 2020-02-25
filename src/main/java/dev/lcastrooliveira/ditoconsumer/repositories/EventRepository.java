package dev.lcastrooliveira.ditoconsumer.repositories;

import dev.lcastrooliveira.ditoconsumer.documents.EventDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ElasticsearchRepository<EventDocument, String> {}
