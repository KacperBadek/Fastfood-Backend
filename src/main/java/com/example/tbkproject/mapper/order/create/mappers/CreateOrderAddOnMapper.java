package com.example.tbkproject.mapper.order.create.mappers;

import com.example.tbkproject.data.documents.support.OrderItemAddOn;


public class CreateOrderAddOnMapper {

    public static OrderItemAddOn toOrderItemAddOn(String name, int quantity, Double additionalPrice, int defaultQuantity) {
        return new OrderItemAddOn(name, additionalPrice, quantity, defaultQuantity);
    }

}
