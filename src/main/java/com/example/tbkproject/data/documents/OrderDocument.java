package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.enums.DeliveryOption;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class OrderDocument {
    @Id
    private String id;
    private String userId;
    private List<OrderItem> items;
    private int orderNumber;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private double totalPrice;
    private DeliveryOption deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;
    private String estimatedTime;

    public OrderDocument(String userId, List<OrderItem> items, int orderNumber, PaymentMethod paymentMethod,
                         OrderStatus status, double totalPrice, DeliveryOption deliveryOption, String deliveryAddress,
                         LocalDateTime orderTime, String estimatedTime) {
        this.userId = userId;
        this.items = items;
        this.orderNumber = orderNumber;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalPrice = totalPrice;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
        this.estimatedTime = estimatedTime;
    }

    public OrderDocument(String userId, List<OrderItem> items, PaymentMethod paymentMethod, double totalPrice, DeliveryOption deliveryOption,
                         String deliveryAddress, LocalDateTime orderTime) {
        this.userId = userId;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
    }

}

