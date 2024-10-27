package com.example.tbkproject;

import com.example.tbkproject.dto.ProductDto;
import com.example.tbkproject.exceptions.exception.product.ProductNotFoundException;
import com.example.tbkproject.service.OrderService;
import com.example.tbkproject.service.ProductService;
import com.example.tbkproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class Controller {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(name));
    }

}
