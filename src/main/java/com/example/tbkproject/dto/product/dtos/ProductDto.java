package com.example.tbkproject.dto.product.dtos;

import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.dto.AddOnDto;
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
public class ProductDto {

    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotBlank
    private String image;
    @NotBlank
    private ProductType type;
    @Positive
    @Min(0)
    private double price;
    @NotBlank
    private boolean available;
    @NotBlank
    private List<String> ingredients;
    @NotBlank
    private List<String> allergens;
    @Positive
    @Min(0)
    private int calories;
    @NotBlank
    private List<AddOnDto> addOns;

}
