package com.example.tbkproject.mapper;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.dto.product.dtos.ProductDto;

public class ProductMapper {
    public static ProductDto toDto(ProductDocument productDocument) {
        return new ProductDto(
                productDocument.getName(),
                productDocument.getDescription(),
                productDocument.getImage(),
                productDocument.getType(),
                productDocument.getPrice(),
                productDocument.isAvailable(),
                productDocument.getIngredients(),
                productDocument.getAllergens(),
                productDocument.getCalories(),
                productDocument.getAddOns()
        );
    }

    public static ProductDocument toDocument(ProductDto productDto) {
        return new ProductDocument(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getType(),
                productDto.getPrice(),
                productDto.isAvailable(),
                productDto.getIngredients(),
                productDto.getAllergens(),
                productDto.getCalories(),
                productDto.getAddOns()
        );
    }
}
