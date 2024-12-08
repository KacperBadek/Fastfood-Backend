package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryDto {

    private List<OrderItemDto> items;
    private OrderStatus status;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private Integer tableNumber;
    private String estimatedTime;
}
