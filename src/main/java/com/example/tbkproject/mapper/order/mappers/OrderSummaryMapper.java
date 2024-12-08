package com.example.tbkproject.mapper.order.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.dto.order.dtos.OrderItemDto;
import com.example.tbkproject.dto.order.dtos.OrderSummaryDto;
import com.example.tbkproject.mapper.AddOnMapper;

import java.util.List;

public class OrderSummaryMapper {

    public static OrderSummaryDto toDto(OrderDocument order, Integer tableNumber) {

        List<OrderItemDto> items = order.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProductName(),
                        item.getSelectedAddOns(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        return new OrderSummaryDto(
                items,
                order.getStatus(),
                order.getTotalPrice(),
                order.getDeliveryOption(),
                order.getDeliveryAddress(),
                tableNumber,
                order.getEstimatedTime());
    }
}
