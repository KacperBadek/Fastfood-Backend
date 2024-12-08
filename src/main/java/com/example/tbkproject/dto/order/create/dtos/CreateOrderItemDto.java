package com.example.tbkproject.dto.order.create.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderItemDto {
    @NotBlank
    private String productName;
    @NotEmpty
    private List<CreateOrderAddOnDto> selectedAddOns;
    @Positive
    @Min(1)
    private int quantity;
}
