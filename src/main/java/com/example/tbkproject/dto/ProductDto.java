package com.example.tbkproject.dto;

import com.example.tbkproject.data.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String name;
    private ProductType type;
    private double price;
    private boolean available;

    public ProductDto(String name, ProductType type, double price, boolean available) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.available = available;
    }
}
