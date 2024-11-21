package com.example.tbkproject.data.enums;

import com.example.tbkproject.exceptions.exception.order.InvalidDeliveryOptionException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum DeliveryOption {
    DINE_IN,
    TAKEOUT,
    DELIVERY;

    @JsonCreator
    public static DeliveryOption fromString(String value) {
        for (DeliveryOption option : DeliveryOption.values()) {
            if (option.name().equalsIgnoreCase(value)) {
                return option;
            }
        }
        throw new InvalidDeliveryOptionException(value);
    }
}
