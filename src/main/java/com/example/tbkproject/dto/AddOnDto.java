package com.example.tbkproject.dto;

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
public class AddOnDto {
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Positive
    @Min(0)
    private int quantity;
    @Positive
    @Min(0)
    private Double additionalPrice;



}
