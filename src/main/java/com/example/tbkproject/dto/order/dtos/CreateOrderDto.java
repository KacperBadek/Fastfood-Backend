package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {

    private String userId;
    private List<OrderItemDto> items;
    private PaymentMethod paymentMethod;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;

    public CreateOrderDto(String userId, List<OrderItemDto> items, PaymentMethod paymentMethod,
                          double totalPrice, DeliveryOption deliveryOption, String deliveryAddress, LocalDateTime orderTime) {

        this.userId = userId;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
    }
}