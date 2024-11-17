package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.ProductDto;
import com.example.tbkproject.exceptions.exception.product.ProductAlreadyExistsException;
import com.example.tbkproject.exceptions.exception.product.ProductNotFoundException;
import com.example.tbkproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductsController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProduct(name));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteProductByName(@PathVariable String name) {
        productService.deleteProductByName(name);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(ProductDto productDto) {
        productService.updateProduct(productDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
