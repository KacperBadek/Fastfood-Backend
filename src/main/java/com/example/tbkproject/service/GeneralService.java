package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.PaymentDocument;
import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.PaymentRepository;
import com.example.tbkproject.dto.general.dtos.MenusDto;
import com.example.tbkproject.dto.general.dtos.PaymentDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
import com.example.tbkproject.exceptions.exception.general.TotalPriceMismatchException;
import com.example.tbkproject.exceptions.exception.order.OrderNotFoundException;
import com.example.tbkproject.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService {

    private final ProductService productService;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public MenusDto getMenus() {
        List<ProductDto> products = productService.getAllProducts();
        List<ProductType> productTypes = Arrays.asList(ProductType.values());

        return new MenusDto(products, productTypes);
    }

    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(PaymentMapper::toDto).toList();
    }

    public void createOrderPayment(PaymentDto dto) {
        OrderDocument order = orderRepository.findById(dto.getOrderId()).orElseThrow(() -> new OrderNotFoundException(dto.getOrderId()));

        if (compareTotalPrice(dto.getTotalPrice(), order.getTotalPrice())) {
            PaymentDocument paymentDocument = new PaymentDocument(dto.getOrderId(), dto.getPaymentMethod(), dto.getTotalPrice());
            paymentRepository.save(paymentDocument);
        } else {
            throw new TotalPriceMismatchException();
        }

    }

    private boolean compareTotalPrice(double paymentPrice, double orderPrice) {
        return paymentPrice == orderPrice;
    }

}
