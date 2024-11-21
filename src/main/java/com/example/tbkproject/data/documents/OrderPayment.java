package com.example.tbkproject.data.documents;

import com.example.tbkproject.data.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayment {

    private String orderId;
    private PaymentMethod paymentMethod;
    private double totalPrice;

}
