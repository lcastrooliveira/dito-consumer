package dev.lcastrooliveira.ditoconsumer.configs;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Getter
@Slf4j
@EnableElasticsearchRepositories(basePackages = "dev.lcastrooliveira.ditoconsumer.repositories")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private int port;
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Bean
    public Client client() {
        TransportClient client = null;
        try {
            final Settings elasticsearchSettings = Settings.builder().put("cluster.name", clusterName).build();
            client = new PreBuiltTransportClient(elasticsearchSettings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            log.error("host: {} port: {} error: {}", host, port, e.getMessage());
        }
        return client;
    }
}
