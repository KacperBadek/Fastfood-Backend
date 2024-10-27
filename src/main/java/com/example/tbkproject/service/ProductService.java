package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.repositories.ProductRepository;
import com.example.tbkproject.dto.ProductDto;
import com.example.tbkproject.exceptions.exception.product.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(String name) throws ProductNotFoundException {
        return productRepository.findByName(name).map(this::mapToDto).orElseThrow(() -> new ProductNotFoundException(name));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private ProductDto mapToDto(ProductDocument product) {
        return new ProductDto(product.getName(), product.getType(), product.getPrice(), product.isAvailable());
    }

    private ProductDocument mapToDocument(ProductDto dto) {
        return new ProductDocument(dto.getName(), dto.getType(), dto.getPrice(), dto.isAvailable());
    }

}
