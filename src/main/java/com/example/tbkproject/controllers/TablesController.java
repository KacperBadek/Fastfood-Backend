package com.example.tbkproject.controllers;

import com.example.tbkproject.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TablesController {

    private final TableService tableService;

    @PutMapping("/{tableId}/orders")
    public ResponseEntity<Void> setOrderOnTable(@PathVariable("tableId") String tableId, @RequestParam String orderId) {
        tableService.setOrderOnTable(tableId, orderId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
