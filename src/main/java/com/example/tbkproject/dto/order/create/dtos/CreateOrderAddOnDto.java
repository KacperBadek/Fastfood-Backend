package com.example.tbkproject.dto.order.create.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderAddOnDto {
    @NotBlank
    @Size(min = 3)
    private String name;
    @Positive
    @Min(0)
    private int quantity;
}
