package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.repositories.ProductRepository;
import com.example.tbkproject.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(String name) {
        return productRepository.findbyName(name).map(this::mapToDto).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private ProductDto mapToDto(ProductDocument product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setAvailable(product.isAvailable());
        return dto;
    }

    private ProductDocument mapToDocument(ProductDto dto) {
        ProductDocument document = new ProductDocument();
        document.setId(dto.getId());
        document.setName(dto.getName());
        document.setPrice(dto.getPrice());
        document.setAvailable(dto.isAvailable());
        return document;
    }

}
