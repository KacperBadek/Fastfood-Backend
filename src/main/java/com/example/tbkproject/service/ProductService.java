package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.repositories.ProductRepository;
import com.example.tbkproject.dto.AddOnDto;
import com.example.tbkproject.dto.product.dtos.ProductDetailsDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
import com.example.tbkproject.exceptions.exception.product.ProductAlreadyExistsException;
import com.example.tbkproject.exceptions.exception.product.ProductNotFoundException;
import com.example.tbkproject.mapper.AddOnMapper;
import com.example.tbkproject.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(String name) {
        return productRepository.findByName(name).map(ProductMapper::toDto).orElseThrow(() -> new ProductNotFoundException(name));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toDto).toList();
    }

    public void addProduct(ProductDto productDto) {
        Optional<ProductDocument> check = productRepository.findByName(productDto.getName());
        if (check.isPresent()) {
            throw new ProductAlreadyExistsException(productDto.getName());
        }
        ProductDocument product = ProductMapper.toDocument(productDto);
        productRepository.save(product);
    }

    public void deleteProductByName(String name) {
        ProductDocument product = productRepository.findByName(name).orElseThrow(() -> new ProductNotFoundException(name));
        productRepository.delete(product);
    }

    public ProductDetailsDto getProductDetails(String id) {
        ProductDocument product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductDetailsDto(product.getIngredients(), product.getAllergens(), product.getCalories());
    }

    public List<AddOnDto> getProductAddOns(String id) {
        ProductDocument product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        return product.getAddOnDocuments().stream().map(AddOnMapper::toDto).toList();
    }

    public void setProductAddOns(String id, List<AddOnDto> addOns) {
        ProductDocument product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setAddOnDocuments(addOns.stream().map(AddOnMapper::toDocument).toList());
    }
}
