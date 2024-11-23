package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.dto.order.dtos.OrderConfirmationDto;

public class OrderConfirmationMapper {

    public static OrderConfirmationDto toDto(OrderDocument order, int tableNumber) {
        return new OrderConfirmationDto(
                order.getOrderNumber(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getDeliveryOption(),
                order.getDeliveryAddress(),
                tableNumber,
                order.getEstimatedTime());
    }

}
