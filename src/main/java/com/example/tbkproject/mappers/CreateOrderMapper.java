package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.OrderItem;
import com.example.tbkproject.dto.CreateOrderDto;
import com.example.tbkproject.dto.OrderDto;
import com.example.tbkproject.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderMapper {

    public static OrderDocument toDocument(CreateOrderDto createOrderDto) {
        List<OrderItem> orderItems = createOrderDto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductId(),
                        itemDto.getSelectedAddOns(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDocument(
                createOrderDto.getUserId(),
                orderItems,
                createOrderDto.getPaymentMethod(),
                createOrderDto.getTotalPrice(),
                createOrderDto.getTableNumber(),
                createOrderDto.getDeliveryOption(),
                createOrderDto.getDeliveryAddress(),
                createOrderDto.getOrderTime()
        );
    }
}
