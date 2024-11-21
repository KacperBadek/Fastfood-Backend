package com.example.tbkproject.service;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.dto.DeliveryOptionDto;
import com.example.tbkproject.dto.order.dtos.CreateOrderDto;
import com.example.tbkproject.dto.order.dtos.OrderDto;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.mappers.CreateOrderMapper;
import com.example.tbkproject.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto getOrderByOrderNumber(String id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private synchronized int generateOrderNumber() {
        Optional<OrderDocument> lastOrder = orderRepository.findFirstByOrderByOrderNumberDesc();

        if (lastOrder.isEmpty() || lastOrder.get().getOrderNumber() >= 999) {
            return 1;
        }

        return lastOrder.get().getOrderNumber() + 1;
    }

    private String countEstimatedTime(LocalDateTime orderTime, DeliveryOption deliveryOption) {
        LocalDateTime estimatedTime = orderTime;

        if (deliveryOption == DeliveryOption.DINE_IN) {
            estimatedTime.plusMinutes(10);
        } else {
            estimatedTime.plusMinutes(30);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return estimatedTime.format(formatter);
    }

    public void createOrder(CreateOrderDto createOrderDto) {
        int orderNumber = generateOrderNumber();
        OrderDocument orderDocument = CreateOrderMapper.toDocument(createOrderDto);
        orderDocument.setOrderNumber(orderNumber);
        orderDocument.setStatus(OrderStatus.PENDING);
        orderDocument.setEstimatedTime(countEstimatedTime(createOrderDto.getOrderTime(), createOrderDto.getDeliveryOption()));

        orderRepository.save(orderDocument);
    }

    public void deleteOrder(String id) {
        OrderDocument order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }

    public void changeOrderStatus(String id) {

    }

    public DeliveryOptionDto getDeliveryOptionFromOrder(String id) {
        OrderDocument order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return new DeliveryOptionDto(order.getDeliveryOption());
    }

    public void setDeliveryOptionForOrder(String id, DeliveryOptionDto deliveryOption) {
        OrderDocument order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setDeliveryOption(deliveryOption.getDeliveryOption());
    }

    public String getOrderEstimatedTime(String id) {
        OrderDocument order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return order.getEstimatedTime();
    }

    public void cancelOrder(String id) {
        OrderDocument order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(OrderStatus.CANCELLED);
    }

}
