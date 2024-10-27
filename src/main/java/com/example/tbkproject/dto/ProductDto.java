package com.example.tbkproject.dto;

import com.example.tbkproject.data.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String name;
    private ProductType type;
    private double price;
    private boolean available;
}
