package com.example.tbkproject.mapper.order.create.mappers;

import com.example.tbkproject.data.documents.support.OrderItemAddOn;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderAddOnDto;
import com.example.tbkproject.service.AddOnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderAddOnMapper {

    private final AddOnService addOnService;

    public OrderItemAddOn toOrderItemAddOn(CreateOrderAddOnDto dto) {
        return new OrderItemAddOn(dto.getName(), addOnService.getAddOnPriceByName(dto.getName()), dto.getQuantity());
    }

}
