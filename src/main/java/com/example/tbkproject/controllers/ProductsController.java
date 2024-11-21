package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.AddOnDto;
import com.example.tbkproject.dto.product.dtos.ProductDetailsDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
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

    @GetMapping("/{id}/customizastions")
    public ResponseEntity<List<AddOnDto>> getProductAddons(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductAddOns(id));
    }

    @PutMapping("/{id}/customizastions")
    public ResponseEntity<List<AddOnDto>> setProductAddons(@PathVariable String id, @RequestBody List<AddOnDto> addons) {
        productService.setProductAddOns(id, addons);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductDetailsDto> getProductDetails(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductDetails(id));
    }

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
