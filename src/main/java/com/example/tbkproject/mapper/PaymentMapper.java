package com.example.tbkproject.mapper;

import com.example.tbkproject.data.documents.PaymentDocument;
import com.example.tbkproject.dto.general.dtos.PaymentDto;

public class PaymentMapper {

    public static PaymentDto toDto(PaymentDocument paymentDocument) {
        return new PaymentDto(paymentDocument.getOrderId(), paymentDocument.getPaymentMethod(), paymentDocument.getTotalPrice());
    }

}
