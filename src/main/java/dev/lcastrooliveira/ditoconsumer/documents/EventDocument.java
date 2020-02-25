package dev.lcastrooliveira.ditoconsumer.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@Document(indexName = "event", type="event")
@Data
public class EventDocument implements Serializable {

    @Id
    private String id;

    @NotNull
    public String event;

    @NotNull
    public ZonedDateTime timestamp;

    public BigDecimal revenue;
    public Map<String, String> customData;
}
