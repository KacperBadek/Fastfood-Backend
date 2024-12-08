package com.example.tbkproject.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOnDto {
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    @Positive
    @Min(0)
    private Double additionalPrice;
}
