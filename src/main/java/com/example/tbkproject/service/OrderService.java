package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderDto;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.exceptions.exception.order.OrderAlreadyPaidForException;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import com.example.tbkproject.mapper.order.create.mappers.CreateOrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderConfirmationMapper;
import com.example.tbkproject.mapper.order.mappers.OrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderSummaryMapper;
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
    private final TableService tableService;
    private final CreateOrderMapper mapper;


    private OrderDocument findOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private Integer getTableNumberByOrder(OrderDocument order) {
        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            return table.getTableNumber();
        }
        return null;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto getOrderByOrderNumber(String id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void createOrder(CreateOrderDto createOrderDto) {
        OrderDocument order = mapper.toDocument(createOrderDto);

        order.setOrderNumber(generateOrderNumber());
        order.setStatus(OrderStatus.PENDING);
        order.setEstimatedTime(countEstimatedTime(createOrderDto.getOrderTime(), createOrderDto.getDeliveryOption()));

        orderRepository.save(order);

        if (createOrderDto.getDeliveryOption() == DeliveryOption.DINE_IN && createOrderDto.getTableNumber() != null) {
            tableService.setOrderOnTableByTableNumber(createOrderDto.getTableNumber(), order.getId());
        }
    }

    public void deleteOrder(String id) {
        OrderDocument order = findOrder(id);
        orderRepository.delete(order);
    }

    public void cancelOrder(String id) {
        OrderDocument order = findOrder(id);

        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            throw new OrderAlreadyPaidForException(id);
        }
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


    public OrderSummaryDto getOrderSummary(String id) {
        OrderDocument order = findOrder(id);
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderSummaryMapper.toDto(order, tableNumber);
    }

    public void modifyOrderSummary(String id, OrderSummaryEditDto dto) {
        OrderDocument order = findOrder(id);
        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductName(),
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
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderConfirmationMapper.toDto(order, tableNumber);
    }


}
