package com.example.tbkproject.service;

import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.dto.MenusDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService {

    private final ProductService productService;

    public MenusDto getMenus(){
        List<ProductDto> products = productService.getAllProducts();
        List<ProductType> productTypes = Arrays.asList(ProductType.values());

        return new MenusDto(products, productTypes);
    }

}
