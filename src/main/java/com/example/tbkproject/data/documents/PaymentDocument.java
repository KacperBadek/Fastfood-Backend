package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
@Getter
@Setter
@NoArgsConstructor
public class PaymentDocument {
    @Id
    private String id;
    private String orderId;
    private PaymentMethod paymentMethod;
    private Double amount;

    public PaymentDocument(String orderId, PaymentMethod paymentMethod, Double amount) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
}
