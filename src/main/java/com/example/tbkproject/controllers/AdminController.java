package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.SalesReportDto;
import com.example.tbkproject.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/sales")
    public ResponseEntity<SalesReportDto> getSalesData(HttpServletRequest request) {
        return ResponseEntity.ok(adminService.getSalesData(request));
    }

}
