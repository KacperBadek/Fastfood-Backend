package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.order.create.dtos.CreateOrderDto;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @GetMapping("/{sessionId}/confirm")
    public ResponseEntity<OrderConfirmationDto> getOrderConfirmation(@PathVariable String sessionId, HttpServletRequest request) {
        return ResponseEntity.ok(orderService.getOrderConfirmation(sessionId, request));
    }

    @PutMapping("/{sessionId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable String sessionId, HttpServletRequest request) {
        orderService.cancelOrder(sessionId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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

    @PostMapping("/create")
    public ResponseEntity<CreateOrderDto> createOrder(@Valid @RequestBody CreateOrderDto createOrderDto, HttpServletRequest request) {
        orderService.createOrder(createOrderDto, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
