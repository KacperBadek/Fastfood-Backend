package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.dto.TableDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 3, max = 100)
    private String deliveryAddress;
    @Positive
    @Min(1)
    private int tableNumber;
}
