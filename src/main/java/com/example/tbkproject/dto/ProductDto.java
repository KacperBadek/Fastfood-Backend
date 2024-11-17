package com.example.tbkproject.dto;

import com.example.tbkproject.data.ProductType;
import com.example.tbkproject.data.documents.AddOn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private String image;
    private ProductType type;
    private double price;
    private boolean available;
    private List<String> ingredients;
    private List<String> allergens;
    private int calories;
    private List<AddOn> addOns;

    public ProductDto(String name, String description, String image, ProductType type, double price, boolean available,
                      List<String> ingredients, List<String> allergens, int calories, List<AddOn> addOns) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.price = price;
        this.available = available;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.calories = calories;
        this.addOns = addOns;
    }
}
