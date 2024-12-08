package com.example.tbkproject.mapper.order.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.dto.order.dtos.OrderDto;
import com.example.tbkproject.dto.order.dtos.OrderItemDto;
import com.example.tbkproject.mapper.AddOnMapper;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMapper {

    public static OrderDto toDto(OrderDocument orderDocument) {
        List<OrderItemDto> orderItemDtos = orderDocument.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProductName(),
                        item.getSelectedAddOns().stream().map(AddOnMapper::toDto).toList(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDto(
                orderDocument.getSessionId(),
                orderItemDtos,
                orderDocument.getOrderNumber(),
                orderDocument.getStatus(),
                orderDocument.getTotalPrice(),
                orderDocument.getDeliveryOption(),
                orderDocument.getDeliveryAddress(),
                orderDocument.getOrderTime(),
                orderDocument.getEstimatedTime()
        );
    }

    public static OrderDocument toDocument(OrderDto orderDto) {
        List<OrderItem> orderItems = orderDto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductName(),
                        itemDto.getSelectedAddOns().stream().map(AddOnMapper::toDocument).toList(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDocument(
                orderDto.getSessionId(),
                orderItems,
                orderDto.getOrderNumber(),
                orderDto.getStatus(),
                orderDto.getTotalPrice(),
                orderDto.getDeliveryOption(),
                orderDto.getDeliveryAddress(),
                orderDto.getOrderTime(),
                orderDto.getEstimatedTime()
        );
    }

}
