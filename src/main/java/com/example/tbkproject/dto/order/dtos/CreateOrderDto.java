package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import jakarta.validation.constraints.*;
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
public class CreateOrderDto {

    @NotBlank
    private String sessionId;
    @NotBlank
    private List<OrderItemDto> items;
    @Positive
    @Min(0)
    private double totalPrice;
    @NotBlank
    private DeliveryOption deliveryOption;
    @NotBlank
    @Size(min = 2, max = 100)
    private String deliveryAddress;
    @NotBlank
    @Past(message = "Creation date must be in the past")
    private LocalDateTime orderTime;

}