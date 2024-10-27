package com.example.tbkproject;

import com.example.tbkproject.service.OrderService;
import com.example.tbkproject.service.ProductService;
import com.example.tbkproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor

public class Controller {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/products")
    public ResponseEntity<String> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
