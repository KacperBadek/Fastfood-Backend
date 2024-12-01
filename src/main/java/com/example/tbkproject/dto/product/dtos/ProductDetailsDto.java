package com.example.tbkproject.dto.product.dtos;

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
public class ProductDetailsDto {

    @NotBlank
    private List<String> ingredients;
    @NotBlank
    private List<String> allergens;
    @Positive
    @Min(0)
    private int calories;

}
