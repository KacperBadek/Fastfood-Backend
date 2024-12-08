package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.documents.support.OrderItemAddOn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private String productName;
    private List<OrderItemAddOn> selectedAddOns;
    private int quantity;
    private double price;
}
