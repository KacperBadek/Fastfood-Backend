package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.OrderItem;
import com.example.tbkproject.dto.OrderDto;
import com.example.tbkproject.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMapper {

    public static OrderDto toDto(OrderDocument orderDocument) {
        List<OrderItemDto> orderItemDtos = orderDocument.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProductId(),
                        item.getSelectedAddOns(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDto(
                orderDocument.getUserId(),
                orderItemDtos,
                orderDocument.getOrderNumber(),
                orderDocument.getPaymentMethod(),
                orderDocument.getStatus(),
                orderDocument.getTotalPrice(),
                orderDocument.getTableNumber(),
                orderDocument.getDeliveryOption(),
                orderDocument.getDeliveryAddress(),
                orderDocument.getOrderTime(),
                orderDocument.getEstimatedTime()
        );
    }

    public static OrderDocument toDocument(OrderDto orderDto) {
        List<OrderItem> orderItems = orderDto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductId(),
                        itemDto.getSelectedAddOns(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDocument(
                orderDto.getUserId(),
                orderItems,
                orderDto.getOrderNumber(),
                orderDto.getPaymentMethod(),
                orderDto.getStatus(),
                orderDto.getTotalPrice(),
                orderDto.getTableNumber(),
                orderDto.getDeliveryOption(),
                orderDto.getDeliveryAddress(),
                orderDto.getOrderTime(),
                orderDto.getEstimatedTime()
        );
    }

}
