package com.example.tbkproject.dto.product.dtos;

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

    private List<String> ingredients;
    private List<String> allergens;
    private int calories;

}
