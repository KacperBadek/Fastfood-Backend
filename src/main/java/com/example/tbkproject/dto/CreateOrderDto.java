package com.example.tbkproject.dto;

import com.example.tbkproject.data.PaymentMethod;
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
    private String tableNumber;
    private String deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;

    public CreateOrderDto(String userId, List<OrderItemDto> items, PaymentMethod paymentMethod,
                          double totalPrice, String tableNumber, String deliveryOption, String deliveryAddress, LocalDateTime orderTime) {

        this.userId = userId;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
    }
}