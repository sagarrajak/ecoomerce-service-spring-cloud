package com.fancystore.orderservice.service;


import com.fancystore.orderservice.dto.OrderLineItemDto;
import com.fancystore.orderservice.dto.OrderRequest;
import com.fancystore.orderservice.entity.Order;
import com.fancystore.orderservice.entity.OrderLineItem;
import com.fancystore.orderservice.error.CustomIllegalArgumentException;
import com.fancystore.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;


    @Transactional
    public Order placeOrder(OrderRequest orderRequest) throws CustomIllegalArgumentException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> list = orderRequest.getOrderLineItemDtos()
                .stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto, order))
                .toList();
        order.setOrderLineItems(list);
        // Call inventory service and place order if product is in inventory
        List<String> skuCodes = orderRequest.getOrderLineItemDtos().stream().map(OrderLineItemDto::getSkuCode).toList();
        HashMap<String, Boolean> inventoryAvailable = this.inventoryService.isInventoryAvailable(skuCodes);
        System.out.println("Output hashmap "+inventoryAvailable);
        System.out.println("Skucodes "+skuCodes);
        List<String> notAvailbleItems = inventoryAvailable.entrySet().stream().filter(e -> !e.getValue()).map(e -> e.getKey()).toList();
        if (!notAvailbleItems.isEmpty()) {
            throw new CustomIllegalArgumentException("Some items are out of stock", notAvailbleItems);
        }
        return orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto, Order order) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setOrder(order);
        return orderLineItem;
    }
}
