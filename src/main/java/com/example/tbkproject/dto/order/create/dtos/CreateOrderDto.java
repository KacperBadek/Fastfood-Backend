package com.example.tbkproject.dto.order.create.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderDto {

    @NotEmpty
    private List<CreateOrderItemDto> items;
    @NotNull
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private Integer tableNumber;
    @NotNull
    @Past(message = "Creation date must be in the past")
    private LocalDateTime orderTime;
}