package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderItemDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty
    private List<CreateOrderItemDto> items;
    @NotNull
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private Integer tableNumber;
}
