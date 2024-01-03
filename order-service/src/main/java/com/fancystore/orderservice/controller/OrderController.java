package com.fancystore.orderservice.controller;

import com.fancystore.orderservice.dto.OrderRequest;
import com.fancystore.orderservice.entity.Order;
import com.fancystore.orderservice.error.CustomIllegalArgumentException;
import com.fancystore.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody OrderRequest orderRequest) throws CustomIllegalArgumentException {
        Order order = this.orderService.placeOrder(orderRequest);
        return order;
    }
}
