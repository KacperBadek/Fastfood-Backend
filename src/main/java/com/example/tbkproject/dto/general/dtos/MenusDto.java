package com.example.tbkproject.dto.general.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.tbkproject.dto.product.dtos.ProductDto;
import com.example.tbkproject.data.enums.ProductType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenusDto {
    private List<ProductDto> products;
    private List<ProductType> productTypes;
}
