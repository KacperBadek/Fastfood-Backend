package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.PaymentDocument;
import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.data.repositories.PaymentRepository;
import com.example.tbkproject.dto.MenusDto;
import com.example.tbkproject.dto.PaymentDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService {

    private final ProductService productService;
    private final PaymentRepository paymentRepository;

    public MenusDto getMenus() {
        List<ProductDto> products = productService.getAllProducts();
        List<ProductType> productTypes = Arrays.asList(ProductType.values());

        return new MenusDto(products, productTypes);
    }

    public void createOrderPayment(PaymentDto dto) {
        PaymentDocument paymentDocument = new PaymentDocument(dto.getOrderId(), dto.getPaymentMethod(), dto.getTotalPrice());
        paymentRepository.save(paymentDocument);
    }

}
