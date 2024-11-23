package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private String sessionId;
    private List<OrderItemDto> items;
    private int orderNumber;
    private OrderStatus status;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;
    private String estimatedTime;

    public OrderDto(String sessionId, List<OrderItemDto> items,
                    int orderNumber, OrderStatus status,
                    double totalPrice, DeliveryOption deliveryOption, String deliveryAddress, LocalDateTime orderTime, String estimatedTime) {

        this.sessionId = sessionId;
        this.items = items;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalPrice = totalPrice;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
        this.estimatedTime = estimatedTime;
    }
}
