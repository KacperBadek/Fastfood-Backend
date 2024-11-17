package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
    @Id
    private String id;
    @Indexed(unique = true)
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

    public ProductDocument(String name, String description, String image, ProductType type, double price, boolean available,
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

