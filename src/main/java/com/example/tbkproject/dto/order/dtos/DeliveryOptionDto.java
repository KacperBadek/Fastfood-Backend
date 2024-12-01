package com.example.tbkproject.dto.order.dtos;

import com.example.tbkproject.data.enums.DeliveryOption;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOptionDto {
    @NotBlank(message = "Delivery option is required")
    private DeliveryOption deliveryOption;
}
