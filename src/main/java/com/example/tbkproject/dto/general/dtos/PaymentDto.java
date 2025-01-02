package com.example.tbkproject.dto.general.dtos;

import com.example.tbkproject.data.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String sessionId;
    private PaymentMethod paymentMethod;
    private Double amount;
}
