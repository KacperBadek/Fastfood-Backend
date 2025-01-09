package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.order.create.dtos.CreateOrderDto;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrderService orderService;

    @GetMapping("/{id}/delivery-options")
    public ResponseEntity<DeliveryOptionDto> getDeliveryOptionFromOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getDeliveryOptionFromOrder(id));
    }

    @PutMapping("/{id}/delivery-options")
    public ResponseEntity<Void> setDeliveryOptionForOrder(@PathVariable String id, @Valid @RequestBody DeliveryOptionDto deliveryOption) {
        orderService.setDeliveryOptionForOrder(id, deliveryOption);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderSummary(id));
    }

    @PutMapping("/{id}/summary")
    public ResponseEntity<Void> modifyOrderSummary(@PathVariable String id, @Valid @RequestBody OrderSummaryEditDto orderSummary) {
        orderService.modifyOrderSummary(id, orderSummary);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/confirm")
    public ResponseEntity<OrderConfirmationDto> getOrderConfirmation(HttpServletRequest request) {
        return ResponseEntity.ok(orderService.getOrderConfirmation(request));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateOrderDto> createOrder(@Valid @RequestBody CreateOrderDto createOrderDto, HttpServletRequest request) {
        log.info("Starting creating order...");
        orderService.createOrder(createOrderDto, request);

        log.info("Order created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/estimated-time")
    public ResponseEntity<String> getOrderEstimatedTime(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderEstimatedTime(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
