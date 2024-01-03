package com.fancystore.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class InventoryService {

    @Value("${services.order-service}")
    private String inventoryService;

    @Autowired
    private WebClient webClient;


    public boolean isInventoryAvailable(String skuCode) {
        return Boolean.TRUE.equals(webClient.get()
                .uri(inventoryService)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    public HashMap isInventoryAvailable(List<String> skuCodes) {
        return webClient.get()
                .uri(
                        builder -> builder
                                .path(inventoryService)
                                .path("items")
                                .queryParam("sku-code", Arrays.toString(skuCodes.toArray()))
                                .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }


}
