package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.CreateOrderDto;
import com.example.tbkproject.dto.OrderDto;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> getOrderByOrderNumber(@PathVariable int orderNumber) {
        return ResponseEntity.ok(orderService.getOrderByOrderNumber(orderNumber));
    }

    @PutMapping("/add")
    public ResponseEntity<CreateOrderDto> createOrder(CreateOrderDto createOrderDto) {
        orderService.createOrder(createOrderDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{orderNumber}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable int orderNumber) {
        orderService.deleteOrder(orderNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
