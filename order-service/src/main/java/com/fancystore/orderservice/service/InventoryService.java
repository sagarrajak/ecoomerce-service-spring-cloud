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


    @Autowired
    private WebClient webClient;


    public boolean isInventoryAvailable(String skuCode) {
        return Boolean.TRUE.equals(webClient.get()
                .uri("/api/inventory")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    public HashMap isInventoryAvailable(List<String> skuCodes) {
        String[] skuCodeArray = skuCodes.toArray(new String[skuCodes.size()]);
        return webClient.get()
                .uri(
                        builder -> builder
                                .path("/api/inventory/items")
                                .queryParam("sku-code", String.join(",", skuCodeArray))
                                .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }


}
