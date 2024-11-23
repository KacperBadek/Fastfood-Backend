package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.MenusDto;
import com.example.tbkproject.dto.PaymentDto;
import com.example.tbkproject.service.GeneralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralService generalService;

    @GetMapping("/menus")
    public ResponseEntity<MenusDto> menus() {
        return ResponseEntity.ok(generalService.getMenus());
    }

    @PostMapping("/payments")
    public ResponseEntity<Void> payments(@Valid @RequestBody PaymentDto paymentDto) {
        generalService.createOrderPayment(paymentDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sessions")
    public ResponseEntity<Void> startSession() {
        return null;
    }

}
