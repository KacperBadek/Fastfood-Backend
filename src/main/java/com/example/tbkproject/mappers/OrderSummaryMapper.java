package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.OrderItem;
import com.example.tbkproject.dto.order.dtos.OrderItemDto;
import com.example.tbkproject.dto.order.dtos.OrderSummaryDto;
import com.example.tbkproject.dto.order.dtos.OrderSummaryEditDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSummaryMapper {

    public static OrderSummaryDto toDto(OrderDocument order, int tableNumber) {

        List<OrderItemDto> items = order.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProductId(),
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
