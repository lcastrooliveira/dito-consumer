package dev.lcastrooliveira.ditoconsumer.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.elasticsearch.core.EntityMapper;

import java.io.IOException;
import java.util.Map;

public class CustomEntityMapper implements EntityMapper {

    private final ObjectMapper mapper;

    public CustomEntityMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String mapToString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    @Override
    public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
        return mapper.readValue(source, clazz);
    }

    @Override
    public Map<String, Object> mapObject(Object o) {
        return null;
    }

    @Override
    public <T> T readObject(Map<String, Object> map, Class<T> aClass) {
        return null;
    }
}