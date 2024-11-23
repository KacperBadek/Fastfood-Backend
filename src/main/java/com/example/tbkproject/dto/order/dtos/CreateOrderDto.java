package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {

    private String sessionId;
    private List<OrderItemDto> items;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;

    public CreateOrderDto(String sessionId, List<OrderItemDto> items,
                          double totalPrice, DeliveryOption deliveryOption, String deliveryAddress, LocalDateTime orderTime) {

        this.sessionId = sessionId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
    }
}