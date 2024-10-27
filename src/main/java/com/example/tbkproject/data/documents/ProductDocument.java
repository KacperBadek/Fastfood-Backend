package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.ProductType;
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
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private ProductType type;
    private double price;
    private boolean available;

    public ProductDocument(String name, ProductType type, double price, boolean available) {

    }
}
