package com.example.tbkproject.mapper.order.create.mappers;

import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.support.OrderItemAddOn;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderItemDto;

import java.util.List;

public class CreateOrderMapper {

    public static OrderItem toOrderItem(String productName, int quantity, double productBasePrice, List<OrderItemAddOn> addOns) {
        double addOnsPricePerUnit = addOns.stream()
                .mapToDouble(addOn -> addOn.getAdditionalPrice() * addOn.getQuantity())
                .sum();

        double unitPrice = productBasePrice + addOnsPricePerUnit;

        return new OrderItem(
                productName,
                addOns,
                quantity,
                unitPrice
        );
    }
}
