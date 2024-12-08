package com.example.tbkproject.mapper.order.create.mappers;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.support.OrderItemAddOn;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderDto;
import com.example.tbkproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateOrderMapper {

    private final CreateOrderAddOnMapper createOrderAddOnMapper;
    private final ProductService productService;

    public OrderDocument toDocument(CreateOrderDto createOrderDto) {


        List<OrderItem> orderItems = createOrderDto.getItems().stream()
                .map(itemDto -> {

                    double productBasePrice = productService.getProduct(itemDto.getProductName()).getPrice();

                    List<OrderItemAddOn> addOns = itemDto.getSelectedAddOns().stream()
                            .map(createOrderAddOnMapper::toOrderItemAddOn)
                            .toList();

                    double addOnsPricePerUnit = addOns.stream()
                            .mapToDouble(addOn -> addOn.getAdditionalPrice() * addOn.getQuantity())
                            .sum();

                    double unitPrice = productBasePrice + addOnsPricePerUnit;

                    return new OrderItem(
                            itemDto.getProductName(),
                            addOns,
                            itemDto.getQuantity(),
                            unitPrice
                    );
                }).collect(Collectors.toList());


        double totalOrderPrice = orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();

        return new OrderDocument(
                createOrderDto.getSessionId(),
                orderItems,
                totalOrderPrice,
                createOrderDto.getDeliveryOption(),
                createOrderDto.getDeliveryAddress(),
                createOrderDto.getOrderTime()
        );
    }
}
