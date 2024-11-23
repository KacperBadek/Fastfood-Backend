package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.OrderItem;
import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.exceptions.exception.order.OrderAlreadyPaidForException;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import com.example.tbkproject.mappers.CreateOrderMapper;
import com.example.tbkproject.mappers.OrderConfirmationMapper;
import com.example.tbkproject.mappers.OrderMapper;
import com.example.tbkproject.mappers.OrderSummaryMapper;
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
    private final TableRepository tableRepository;

    private OrderDocument findOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private int getTableNumberByOrder(OrderDocument order) {
        int tableNumber = -1;

        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            tableNumber = table.getTableNumber();
        }
        return tableNumber;
    }

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

    private double countTotalPrice(List<OrderItemDto> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }


    public void createOrder(CreateOrderDto createOrderDto) {
        OrderDocument order = CreateOrderMapper.toDocument(createOrderDto);

        order.setOrderNumber(generateOrderNumber());
        order.setTotalPrice(countTotalPrice(createOrderDto.getItems()));
        order.setStatus(OrderStatus.PENDING);
        order.setEstimatedTime(countEstimatedTime(createOrderDto.getOrderTime(), createOrderDto.getDeliveryOption()));

        orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        OrderDocument order = findOrder(id);
        orderRepository.delete(order);
    }

    public DeliveryOptionDto getDeliveryOptionFromOrder(String id) {
        OrderDocument order = findOrder(id);
        return new DeliveryOptionDto(order.getDeliveryOption());
    }

    public void setDeliveryOptionForOrder(String id, DeliveryOptionDto deliveryOption) {
        OrderDocument order = findOrder(id);
        order.setDeliveryOption(deliveryOption.getDeliveryOption());
    }

    public String getOrderEstimatedTime(String id) {
        OrderDocument order = findOrder(id);
        return order.getEstimatedTime();
    }

    public void cancelOrder(String id) {
        OrderDocument order = findOrder(id);

        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            throw new OrderAlreadyPaidForException(id);
        }
    }

    public OrderSummaryDto getOrderSummary(String id) {
        OrderDocument order = findOrder(id);
        int tableNumber = getTableNumberByOrder(order);

        return OrderSummaryMapper.toDto(order, tableNumber);
    }

    public void modifyOrderSummary(String id, OrderSummaryEditDto dto) {
        OrderDocument order = findOrder(id);
        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductId(),
                        itemDto.getSelectedAddOns(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .toList();

        order.setItems(items);
        order.setDeliveryOption(dto.getDeliveryOption());
        order.setDeliveryAddress(null);

        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            table.setOrderId(order.getId());
        } else if (order.getDeliveryOption() == DeliveryOption.DELIVERY) {
            order.setDeliveryAddress(dto.getDeliveryAddress());
        }
    }

    public OrderConfirmationDto getOrderConfirmation(String id) {
        OrderDocument order = findOrder(id);
        int tableNumber = getTableNumberByOrder(order);

        return OrderConfirmationMapper.toDto(order, tableNumber);
    }


}
