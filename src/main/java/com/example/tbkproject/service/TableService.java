package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public void setOrderOnTable(String tableId, String orderId) {
        TableDocument table = tableRepository.findById(tableId).orElseThrow(() -> new TableNotFoundException(tableId));

        table.setOrderId(orderId);
    }

}
