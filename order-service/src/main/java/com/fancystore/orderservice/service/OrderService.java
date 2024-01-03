package com.fancystore.orderservice.service;


import com.fancystore.orderservice.dto.OrderLineItemDto;
import com.fancystore.orderservice.dto.OrderRequest;
import com.fancystore.orderservice.entity.Order;
import com.fancystore.orderservice.entity.OrderLineItem;
import com.fancystore.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> list = orderRequest.getOrderLineItemDtos()
                .stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto, order))
                .toList();
        order.setOrderLineItems(list);
        return orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto, Order order) {
//        System.out.println(orderLineItemDto);
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setOrder(order);
        return orderLineItem;
    }
}
