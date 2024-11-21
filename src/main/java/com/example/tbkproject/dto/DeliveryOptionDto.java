package com.example.tbkproject.dto;

import com.example.tbkproject.data.enums.DeliveryOption;
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
    @NotNull(message = "Delivery option is required")
    private DeliveryOption deliveryOption;
}
