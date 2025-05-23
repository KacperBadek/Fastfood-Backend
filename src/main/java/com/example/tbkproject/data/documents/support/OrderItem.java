package com.example.tbkproject.data.documents.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String productName;
    private List<OrderItemAddOn> selectedAddOns;
    private int quantity;
    private double price;
}
