package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.MenusDto;
import com.example.tbkproject.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<Void> payments() {
        return null;
    }

    @PostMapping("/sessions")
    public ResponseEntity<Void> startSession() {
        return null;
    }

    @GetMapping("/errors")
    public ResponseEntity<Void> getErrors() {
        return null;
    }

}
