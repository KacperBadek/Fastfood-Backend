package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.dto.AddOnDto;
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
public class OrderItemDto {
    @NotBlank
    private String productId;
    @NotBlank
    private List<AddOnDto> selectedAddOns;
    @Positive
    @Min(1)
    private int quantity;
    @Positive
    @Min(0)
    private double price;

}
