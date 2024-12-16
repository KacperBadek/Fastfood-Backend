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
        tableRepository.findByOrderId(orderId).ifPresent(existingTable -> {
            existingTable.setOrderId(null);
            tableRepository.save(existingTable);
        });

        TableDocument table = tableRepository.findById(tableId).orElseThrow(TableNotFoundException::new);

        table.setOrderId(orderId);
        tableRepository.save(table);
    }

    public void setOrderOnTableByTableNumber(Integer tableNumber, String orderId) {
        tableRepository.findByOrderId(orderId).ifPresent(existingTable -> {
            existingTable.setOrderId(null);
            tableRepository.save(existingTable);
        });

        TableDocument table = tableRepository.findByTableNumber(tableNumber).orElseThrow(TableNotFoundException::new);

        table.setOrderId(orderId);
        tableRepository.save(table);
    }

}
