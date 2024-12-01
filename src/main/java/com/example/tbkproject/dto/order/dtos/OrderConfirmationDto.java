package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmationDto {

    @Positive
    @Min(1)
    private int orderNumber;
    @NotBlank
    private OrderStatus status;
    @Positive
    @Min(0)
    private double totalPrice;
    @NotBlank
    private DeliveryOption deliveryOption;
    @NotBlank
    @Size(min = 3, max = 100)
    private String deliveryAddress;
    @Positive
    @Min(1)
    private int tableNumber;
    @NotBlank
    private String estimatedTime;
}
