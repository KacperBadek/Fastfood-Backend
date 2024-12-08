package com.example.tbkproject.data.documents.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemAddOn {
    private String name;
    private Double additionalPrice;
    private int quantity;
}
