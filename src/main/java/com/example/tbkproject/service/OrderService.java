package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.documents.support.AddOn;
import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.ProductRepository;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.dto.AddOnDto;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.exceptions.exception.order.AddOnInItemMismatchException;
import com.example.tbkproject.exceptions.exception.order.ItemInOrderMismatchException;
import com.example.tbkproject.exceptions.exception.order.OrderAlreadyPaidForException;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import com.example.tbkproject.mapper.AddOnMapper;
import com.example.tbkproject.mapper.order.mappers.CreateOrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderConfirmationMapper;
import com.example.tbkproject.mapper.order.mappers.OrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final TableService tableService;


    private OrderDocument findOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private Integer getTableNumberByOrder(OrderDocument order) {
        Integer tableNumber = null;

        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            tableNumber = table.getTableNumber();
        }
        return tableNumber;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto getOrderByOrderNumber(String id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void createOrder(CreateOrderDto createOrderDto) {
        validateOrderItems(createOrderDto.getItems());

        OrderDocument order = CreateOrderMapper.toDocument(createOrderDto);

        order.setOrderNumber(generateOrderNumber());
        order.setTotalPrice(countTotalPrice(createOrderDto.getItems()));
        order.setStatus(OrderStatus.PENDING);
        order.setEstimatedTime(countEstimatedTime(createOrderDto.getOrderTime(), createOrderDto.getDeliveryOption()));

        orderRepository.save(order);

        if (createOrderDto.getDeliveryOption() == DeliveryOption.DINE_IN && createOrderDto.getTableNumber() != null) {
            tableService.setOrderOnTableByTableNumber(createOrderDto.getTableNumber(), order.getId());
        }
    }

    public void deleteOrder(String id) {
        OrderDocument order = findOrder(id);
        orderRepository.delete(order);
    }

    public void cancelOrder(String id) {
        OrderDocument order = findOrder(id);

        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            throw new OrderAlreadyPaidForException(id);
        }
    }

    private synchronized int generateOrderNumber() {
        Optional<OrderDocument> lastOrder = orderRepository.findFirstByOrderByOrderNumberDesc();

        if (lastOrder.isEmpty() || lastOrder.get().getOrderNumber() >= 999) {
            return 1;
        }

        return lastOrder.get().getOrderNumber() + 1;
    }


    private String countEstimatedTime(LocalDateTime orderTime, DeliveryOption deliveryOption) {
        LocalDateTime estimatedTime = orderTime;

        if (deliveryOption == DeliveryOption.DINE_IN) {
            estimatedTime.plusMinutes(10);
        } else {
            estimatedTime.plusMinutes(30);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return estimatedTime.format(formatter);
    }

    private double countTotalPrice(List<OrderItemDto> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> {
                    double basePrice = item.getPrice() * item.getQuantity();

                    double addOnsPrice = item.getSelectedAddOns().stream()
                            .mapToDouble(addon -> addon.getAdditionalPrice() * addon.getQuantity())
                            .sum() * item.getQuantity();

                    return basePrice + addOnsPrice;
                })
                .sum();
    }


    private void validateOrderItems(List<OrderItemDto> orderItems) {
        List<String> validProductNames = productRepository.findAll().stream()
                .map(ProductDocument::getName)
                .toList();

        Map<String, ProductDocument> productsByName = productRepository.findAll().stream()
                .collect(Collectors.toMap(ProductDocument::getName, product -> product));

        for (OrderItemDto item : orderItems) {
            if (!validProductNames.contains(item.getProductName())) {
                throw new ItemInOrderMismatchException(item.getProductName());
            }

            ProductDocument product = productsByName.get(item.getProductName());
            if (product.getPrice() != item.getPrice()) {
                throw new ItemInOrderMismatchException(item.getProductName());
            }

            validateAddOns(item.getSelectedAddOns(), product.getAddOns());
        }

    }

    private void validateAddOns(List<AddOnDto> selectedAddOns, List<AddOn> validAddOns) {
        Map<String, Double> validAddOnsMap = validAddOns.stream()
                .collect(Collectors.toMap(AddOn::getName, AddOn::getAdditionalPrice));

        for (AddOnDto selectedAddOn : selectedAddOns) {
            if (!validAddOnsMap.containsKey(selectedAddOn.getName())) {
                throw new AddOnInItemMismatchException(selectedAddOn.getName());
            }

            Double expectedPrice = validAddOnsMap.get(selectedAddOn.getName());
            if (!expectedPrice.equals(selectedAddOn.getAdditionalPrice())) {
                throw new AddOnInItemMismatchException(selectedAddOn.getName());
            }

        }

    }

    public DeliveryOptionDto getDeliveryOptionFromOrder(String id) {
        OrderDocument order = findOrder(id);
        return new DeliveryOptionDto(order.getDeliveryOption());
    }

    public void setDeliveryOptionForOrder(String id, DeliveryOptionDto deliveryOption) {
        OrderDocument order = findOrder(id);
        order.setDeliveryOption(deliveryOption.getDeliveryOption());
    }

    public String getOrderEstimatedTime(String id) {
        OrderDocument order = findOrder(id);
        return order.getEstimatedTime();
    }


    public OrderSummaryDto getOrderSummary(String id) {
        OrderDocument order = findOrder(id);
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderSummaryMapper.toDto(order, tableNumber);
    }

    public void modifyOrderSummary(String id, OrderSummaryEditDto dto) {
        OrderDocument order = findOrder(id);
        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> new OrderItem(
                        itemDto.getProductName(),
                        itemDto.getSelectedAddOns().stream().map(AddOnMapper::toDocument).toList(),
                        itemDto.getQuantity(),
                        itemDto.getPrice()
                ))
                .toList();

        order.setItems(items);
        order.setDeliveryOption(dto.getDeliveryOption());
        order.setDeliveryAddress(null);

        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            table.setOrderId(order.getId());
        } else if (order.getDeliveryOption() == DeliveryOption.DELIVERY) {
            order.setDeliveryAddress(dto.getDeliveryAddress());
        }
    }

    public OrderConfirmationDto getOrderConfirmation(String id) {
        OrderDocument order = findOrder(id);
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderConfirmationMapper.toDto(order, tableNumber);
    }


}
