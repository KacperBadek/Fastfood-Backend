package com.example.tbkproject.dto;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesReportDto {
    private long totalOrders;
    private double totalRevenue;
    private Map<OrderStatus, Long> ordersByStatus;
    private Map<DeliveryOption, Long> ordersByDeliveryOption;
}
