package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @NotBlank
    private List<OrderItemDto> items;
    @NotBlank
    private OrderStatus status;
    @Positive
    @Min(0)
    private double totalPrice;
    @NotBlank
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private Integer tableNumber;
    @NotBlank
    private String estimatedTime;
}
