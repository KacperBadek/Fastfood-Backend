package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.documents.support.OrderItem;
import com.example.tbkproject.data.documents.support.OrderItemAddOn;
import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderAddOnDto;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderDto;
import com.example.tbkproject.dto.order.create.dtos.CreateOrderItemDto;
import com.example.tbkproject.dto.order.dtos.*;
import com.example.tbkproject.exceptions.exception.order.InvalidDataException;
import com.example.tbkproject.exceptions.exception.order.OrderAlreadyPaidForException;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.exceptions.exception.order.OrderWithSessionIdNotFoundException;
import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import com.example.tbkproject.mapper.order.create.mappers.CreateOrderAddOnMapper;
import com.example.tbkproject.mapper.order.create.mappers.CreateOrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderConfirmationMapper;
import com.example.tbkproject.mapper.order.mappers.OrderMapper;
import com.example.tbkproject.mapper.order.mappers.OrderSummaryMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final TableService tableService;
    private final AddOnService addOnService;
    private final ProductService productService;
    private final GeneralService generalService;


    private OrderDocument findOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private OrderDocument findOrderBySessionId(String sessionId) {
        return orderRepository.findBySessionId(sessionId).orElseThrow(() -> new OrderWithSessionIdNotFoundException(sessionId));
    }

    private Integer getTableNumberByOrder(OrderDocument order) {
        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            TableDocument table = tableRepository.findByOrderId(order.getId())
                    .orElseThrow(TableNotFoundException::new);
            return table.getTableNumber();
        }
        return null;
    }

    private boolean isDataValid(Integer tableNumber, DeliveryOption deliveryOption, String Address) {
        if (deliveryOption == DeliveryOption.DINE_IN) {
            return tableNumber != null && tableRepository.findByTableNumber(tableNumber).isPresent();
        } else if (deliveryOption == DeliveryOption.DELIVERY) {
            return !Address.isEmpty();
        }
        return true;
    }

    private String checkSession(HttpServletRequest request) {
        String currentSessionId = generalService.getSessionInfo(request);

        if (orderRepository.findBySessionId(currentSessionId).isPresent()) {
            throw new SessionAuthenticationException("Invalid Session");
        }
        return currentSessionId;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto getOrderById(String id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private void handleDineInOrder(Integer tableNumber, OrderDocument order) {
        if (order.getDeliveryOption() == DeliveryOption.DINE_IN) {
            tableService.setOrderOnTableByTableNumber(tableNumber, order.getId());
        }
    }

    private List<OrderItemAddOn> getAddonList(List<CreateOrderAddOnDto> addOns) {
        return addOns.stream()
                .map(addOnDto -> {
                    double additionalPrice = addOnService.getAddOnPriceByName(addOnDto.getName());
                    int defaultQuantity = addOnService.getAddOnDefaultQuantityByName(addOnDto.getName());
                    return CreateOrderAddOnMapper.toOrderItemAddOn(addOnDto.getName(), addOnDto.getQuantity(), additionalPrice, defaultQuantity);
                })
                .toList();
    }

    private List<OrderItem> getItemList(List<CreateOrderItemDto> items) {
        return items.stream()
                .map(itemDto -> {
                    double productBasePrice = productService.getProduct(itemDto.getProductName()).getPrice();

                    List<OrderItemAddOn> addOns = getAddonList(itemDto.getSelectedAddOns());

                    return CreateOrderMapper.toOrderItem(itemDto.getProductName(), itemDto.getQuantity(), productBasePrice, addOns);
                })
                .toList();
    }

    public void createOrder(CreateOrderDto dto, HttpServletRequest request) {
        if (isDataValid(dto.getTableNumber(), dto.getDeliveryOption(), dto.getDeliveryAddress())) {
            List<OrderItem> orderItems = getItemList(dto.getItems());

            double totalPrice = orderItems.stream()
                    .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                    .sum();

            OrderDocument order = new OrderDocument();
            order.setSessionId(checkSession(request));
            order.setItems(orderItems);
            order.setTotalPrice(totalPrice);
            order.setOrderNumber(generateOrderNumber());
            order.setStatus(OrderStatus.PENDING);
            order.setDeliveryOption(dto.getDeliveryOption());
            if (dto.getDeliveryOption() == DeliveryOption.DELIVERY) {
                order.setDeliveryAddress(dto.getDeliveryAddress());
            }
            order.setOrderTime(dto.getOrderTime());
            order.setEstimatedTime(countEstimatedTime(dto.getOrderTime(), dto.getDeliveryOption()));

            orderRepository.save(order);
            handleDineInOrder(dto.getTableNumber(), order);
        } else {
            throw new InvalidDataException();
        }

    }

    public void deleteOrder(String id) {
        OrderDocument order = findOrderById(id);
        orderRepository.delete(order);
    }

    public void cancelOrder(String id) {
        OrderDocument order = findOrderById(id);

        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
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
        LocalDateTime estimatedTime = switch (deliveryOption) {
            case DINE_IN, TAKEOUT -> orderTime.plusMinutes(10);
            case DELIVERY -> orderTime.plusMinutes(30);
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return estimatedTime.format(formatter);
    }


    public DeliveryOptionDto getDeliveryOptionFromOrder(String id) {
        OrderDocument order = findOrderById(id);
        return new DeliveryOptionDto(order.getDeliveryOption());
    }

    public void setDeliveryOptionForOrder(String id, DeliveryOptionDto deliveryOption) {
        OrderDocument order = findOrderById(id);
        order.setDeliveryOption(deliveryOption.getDeliveryOption());
        orderRepository.save(order);
    }

    public String getOrderEstimatedTime(String id) {
        OrderDocument order = findOrderById(id);
        return order.getEstimatedTime();
    }


    public OrderSummaryDto getOrderSummary(String id) {
        OrderDocument order = findOrderById(id);
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderSummaryMapper.toDto(order, tableNumber);
    }

    public void modifyOrderSummary(String id, OrderSummaryEditDto dto) {
        OrderDocument order = findOrderById(id);

        if (isDataValid(dto.getTableNumber(), dto.getDeliveryOption(), dto.getDeliveryAddress())) {
            List<OrderItem> updatedItems = getItemList(dto.getItems());

            double totalPrice = updatedItems.stream()
                    .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                    .sum();

            order.setItems(updatedItems);
            order.setTotalPrice(totalPrice);
            order.setDeliveryOption(dto.getDeliveryOption());
            if (dto.getDeliveryOption() == DeliveryOption.DELIVERY) {
                order.setDeliveryAddress(dto.getDeliveryAddress());
            }
            order.setEstimatedTime(countEstimatedTime(order.getOrderTime(), dto.getDeliveryOption()));

            orderRepository.save(order);
            handleDineInOrder(dto.getTableNumber(), order);
        } else {
            throw new InvalidDataException();
        }
    }

    public OrderConfirmationDto getOrderConfirmation(HttpServletRequest request) {
        String currentSessionId = generalService.getSessionInfo(request);

        OrderDocument order = findOrderBySessionId(currentSessionId);
        Integer tableNumber = getTableNumberByOrder(order);

        return OrderConfirmationMapper.toDto(order, tableNumber);
    }


}
