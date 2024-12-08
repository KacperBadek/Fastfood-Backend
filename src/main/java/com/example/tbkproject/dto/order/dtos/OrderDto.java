package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
