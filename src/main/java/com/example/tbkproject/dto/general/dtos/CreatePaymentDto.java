package com.example.tbkproject.dto.general.dtos;

import com.example.tbkproject.data.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDto {
    @NotNull
    private PaymentMethod paymentMethod;
}
