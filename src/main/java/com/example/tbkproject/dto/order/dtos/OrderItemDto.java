package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.documents.AddOn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
    private String productId;
    private List<AddOn> selectedAddOns;
    private int quantity;
    private double price;

    public OrderItemDto(String productId, List<AddOn> selectedAddOns, int quantity, double price) {
        this.productId = productId;
        this.selectedAddOns = selectedAddOns;
        this.quantity = quantity;
        this.price = price;
    }
}
