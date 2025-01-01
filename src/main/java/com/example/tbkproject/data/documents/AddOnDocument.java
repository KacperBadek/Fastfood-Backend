package com.example.tbkproject.data.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOnDocument {
    @Id
    private String id;
    private String name;
    private Double additionalPrice;
    private int defaultQuantity;

    public AddOnDocument(String name, Double additionalPrice, int defaultQuantity) {
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.defaultQuantity = defaultQuantity;
    }
}

