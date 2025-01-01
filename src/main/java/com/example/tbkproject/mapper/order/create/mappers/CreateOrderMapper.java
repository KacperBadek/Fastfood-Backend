package com.example.tbkproject.mapper.order.create.mappers;

import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.support.OrderItemAddOn;

import java.util.List;

public class CreateOrderMapper {

    public static OrderItem toOrderItem(String productName, int quantity, double productBasePrice, List<OrderItemAddOn> addOns) {
        double addOnsPricePerUnit = addOns.stream().mapToDouble(addOn -> {
            int additionalQuantity = Math.max(0, addOn.getQuantity() - addOn.getDefaultQuantity());
            return addOn.getAdditionalPrice() * additionalQuantity;
        }).sum();

        double unitPrice = productBasePrice + addOnsPricePerUnit;

        return new OrderItem(
                productName,
                addOns,
                quantity,
                unitPrice
        );
    }
}
