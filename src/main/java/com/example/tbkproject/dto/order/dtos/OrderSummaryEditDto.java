package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryEditDto {

    @NotBlank
    private List<OrderItemDto> items;
    @NotBlank
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private Integer tableNumber;
}
