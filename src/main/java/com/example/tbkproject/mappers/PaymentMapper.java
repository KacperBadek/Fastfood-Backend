package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.PaymentDocument;
import com.example.tbkproject.dto.PaymentDto;

public class PaymentMapper {

    public static PaymentDto toDto(PaymentDocument paymentDocument) {
        return new PaymentDto(paymentDocument.getOrderId(), paymentDocument.getPaymentMethod(), paymentDocument.getTotalPrice());
    }

}
