package com.example.tbkproject.dto.product.dtos;

import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.dto.AddOnDto;
import jakarta.validation.constraints.*;
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
    @NotNull
    private ProductType type;
    @Positive
    @Min(0)
    private double price;
    private boolean available;
    @NotEmpty
    private List<String> ingredients;
    @NotEmpty
    private List<String> allergens;
    @Positive
    @Min(0)
    private int calories;
    @NotEmpty
    private List<AddOnDto> addOns;

}
