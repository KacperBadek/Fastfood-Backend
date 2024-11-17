package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.dto.CreateOrderDto;
import com.example.tbkproject.dto.OrderDto;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.mappers.CreateOrderMapper;
import com.example.tbkproject.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto getOrderByOrderNumber(int orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber).map(OrderMapper::toDto).orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }

    public synchronized int generateOrderNumber() {
        Optional<OrderDocument> lastOrder = orderRepository.findFirstByOrderByOrderNumberDesc();

        if (lastOrder.isEmpty() || lastOrder.get().getOrderNumber() >= 999) {
            return 1;
        }

        return lastOrder.get().getOrderNumber() + 1;
    }

    public void createOrder(CreateOrderDto createOrderDto) {
        int orderNumber = generateOrderNumber();
        OrderDocument orderDocument = CreateOrderMapper.toDocument(createOrderDto);
        orderDocument.setOrderNumber(orderNumber);

        orderRepository.save(orderDocument);
    }

    public void deleteOrder(int orderNumber) {
        OrderDocument order = orderRepository.findByOrderNumber(orderNumber).orElseThrow(() -> new OrderNotFoundException(orderNumber));
        orderRepository.delete(order);
    }

    public void changeOrderStatus(int orderNumber) {

    }

}
