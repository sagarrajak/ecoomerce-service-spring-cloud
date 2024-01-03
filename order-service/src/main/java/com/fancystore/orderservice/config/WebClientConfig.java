package com.fancystore.orderservice.config;

import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG, AdvancedByteBufFormat.SIMPLE);
        ClientHttpConnector conn = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder().clientConnector(conn).baseUrl("http://localhost:8082").build();
    }
}
