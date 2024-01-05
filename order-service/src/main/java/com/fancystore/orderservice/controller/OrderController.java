package com.fancystore.orderservice.controller;

import com.fancystore.orderservice.dto.OrderRequest;
import com.fancystore.orderservice.dto.OrderResponse;
import com.fancystore.orderservice.entity.Order;
import com.fancystore.orderservice.error.CustomIllegalArgumentException;
import com.fancystore.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.aspectj.weaver.ast.Or;
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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public OrderResponse<Order> placeOrder(@RequestBody OrderRequest orderRequest) throws CustomIllegalArgumentException {
        Order order = this.orderService.placeOrder(orderRequest);
        return new OrderResponse<>(order, "Order successfully!");
    }

    public OrderResponse<Order> fallbackMethod(@RequestBody OrderRequest orderRequest) throws CustomIllegalArgumentException {
        return new OrderResponse<>(null, "Something went wrong, unable to place order!");
    }

}
