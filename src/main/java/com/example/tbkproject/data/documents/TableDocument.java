package com.example.tbkproject.data.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class TableDocument {
    @Id
    private String id;
    private String orderId;
    private int tableNumber;

   public TableDocument(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
