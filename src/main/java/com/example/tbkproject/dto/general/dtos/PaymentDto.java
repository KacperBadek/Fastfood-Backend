package com.example.tbkproject.dto.general.dtos;

import com.example.tbkproject.data.enums.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @NotBlank
    private String orderId;
    @NotNull
    private PaymentMethod paymentMethod;
    @Positive
    @Min(0)
    private double totalPrice;

}
