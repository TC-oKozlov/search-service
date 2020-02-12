package com.tigerconnect.searchservice;

import org.apache.camel.CamelContext;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

import javax.inject.Singleton;

@Configuration
@EnableReactiveElasticsearchRepositories(basePackages = "com.tigerconnect.springreactivees.repositories")
@ComponentScan(basePackages = {"com.tigerconnect.searchservice"})
public class Config extends AbstractReactiveElasticsearchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Value("${pulsar.broker.url}")
    private String pulsarBrokerUrl;

    @Autowired
    CamelContext camelContext;

    /**
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.reactive
     */
    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9201")
                .withWebClientConfigurer(webClient -> {
                    ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                            .codecs(configurer -> configurer.defaultCodecs()
                                    .maxInMemorySize(-1))
                            .build();
                    return webClient.mutate().exchangeStrategies(exchangeStrategies).build();
                })
                .build();

        return ReactiveRestClients.create(clientConfiguration);
    }

    @Bean
    @Singleton
    public PulsarClient createPulsarClient() {
        try {
            PulsarClient pulsarClient = PulsarClient.builder()
                    .serviceUrl(pulsarBrokerUrl)
                    .build();
            camelContext.getRegistry().bind("pulsarClient", pulsarClient);
            return pulsarClient;
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }


}
