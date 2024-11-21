package com.example.tbkproject.dto.product.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailsDto {

    private List<String> ingredients;
    private List<String> allergens;
    private int calories;


    public ProductDetailsDto(List<String> ingredients, List<String> allergens, int calories) {
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.calories = calories;
    }
}
