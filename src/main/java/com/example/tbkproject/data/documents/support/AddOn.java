package com.example.tbkproject.data.documents.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOn {
    private String name;
    private int quantity;
    private Double additionalPrice;

    public AddOn(String name, Double additionalPrice) {
        this.name = name;
        this.quantity = 0;
        this.additionalPrice = additionalPrice;
    }

}

