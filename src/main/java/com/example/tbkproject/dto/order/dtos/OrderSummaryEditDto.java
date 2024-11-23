package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.dto.TableDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryEditDto {

    private List<OrderItemDto> items;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private int tableNumber;
}
