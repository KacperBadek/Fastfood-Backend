package com.example.tbkproject.mapper.order.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.dto.order.dtos.CreateOrderDto;
import com.example.tbkproject.mapper.AddOnMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderMapper {

    public static OrderDocument toDocument(CreateOrderDto createOrderDto) {
        List<OrderItem> orderItems = createOrderDto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductId(),
                        itemDto.getSelectedAddOns().stream().map(AddOnMapper::toDocument).toList(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDocument(
                createOrderDto.getSessionId(),
                orderItems,
                createOrderDto.getTotalPrice(),
                createOrderDto.getDeliveryOption(),
                createOrderDto.getDeliveryAddress(),
                createOrderDto.getOrderTime()
        );
    }
}
