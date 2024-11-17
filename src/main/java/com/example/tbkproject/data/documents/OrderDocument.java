package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.OrderStatus;
import com.example.tbkproject.data.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDocument {
    @Id
    private String id;
    private String userId;
    private List<OrderItem> items;
    @Indexed(unique = true)
    private int orderNumber;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private double totalPrice;
    private String tableNumber;
    private String deliveryOption;
    private String deliveryAddress;
    private LocalDateTime orderTime;
    private String estimatedTime;

    public OrderDocument(String userId, List<OrderItem> items, int orderNumber, PaymentMethod paymentMethod,
                         OrderStatus status, double totalPrice, String tableNumber, String deliveryOption, String deliveryAddress,
                         LocalDateTime orderTime, String estimatedTime) {
        this.userId = userId;
        this.items = items;
        this.orderNumber = orderNumber;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
        this.estimatedTime = estimatedTime;
    }

    public OrderDocument(String userId, List<OrderItem> items, PaymentMethod paymentMethod, double totalPrice, String tableNumber, String deliveryOption,
                         String deliveryAddress, LocalDateTime orderTime) {
        this.userId = userId;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.deliveryOption = deliveryOption;
        this.deliveryAddress = deliveryAddress;
        this.orderTime = orderTime;
    }

}

