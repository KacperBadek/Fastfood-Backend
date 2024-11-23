package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmationDto {

    private int orderNumber;
    private OrderStatus status;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private int tableNumber;
    private String estimatedTime;
}
