package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.dto.SalesReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrderRepository orderRepository;

    public SalesReportDto getSalesData() {
        List<OrderDocument> orders = orderRepository.findAll();

        long totalOrders = orders.size();
        double totalRevenue = orders.stream().mapToDouble(OrderDocument::getTotalPrice).sum();

        Map<OrderStatus, Long> ordersByStatus = orders.stream()
                .collect(Collectors.groupingBy(OrderDocument::getStatus, Collectors.counting()));

        Map<DeliveryOption, Long> ordersByDeliveryOption = orders.stream()
                .collect(Collectors.groupingBy(OrderDocument::getDeliveryOption, Collectors.counting()));

        return new SalesReportDto(totalOrders, totalRevenue, ordersByStatus, ordersByDeliveryOption);
    }

}
