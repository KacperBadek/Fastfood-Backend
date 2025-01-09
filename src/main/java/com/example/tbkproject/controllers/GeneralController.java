package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.general.dtos.CreatePaymentDto;
import com.example.tbkproject.dto.general.dtos.MenusDto;
import com.example.tbkproject.dto.general.dtos.PaymentDto;
import com.example.tbkproject.service.GeneralService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class GeneralController {

    private final GeneralService generalService;

    @GetMapping("/menus")
    public ResponseEntity<MenusDto> menus() {
        return ResponseEntity.ok(generalService.getMenus());
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> payments() {
        return ResponseEntity.ok(generalService.getAllPayments());
    }

    @PostMapping("/payments")
    public ResponseEntity<Void> payments(@Valid @RequestBody CreatePaymentDto createPaymentDto, HttpServletRequest request) {
        generalService.createOrderPayment(createPaymentDto, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/session/start")
    public ResponseEntity<Void> startSession(HttpServletRequest request) {
        log.info("Starting session");
        generalService.startSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/session/end")
    public ResponseEntity<Void> endSession(HttpServletRequest request) {
        log.info("Ending session");
        generalService.endSession(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/session")
    public ResponseEntity<String> getSessionInfo(HttpServletRequest request) {
        return ResponseEntity.ok(generalService.getSessionInfo(request));
    }

}
