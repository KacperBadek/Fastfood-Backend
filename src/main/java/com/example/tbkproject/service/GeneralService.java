package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.OrderDocument;
import com.example.tbkproject.data.documents.PaymentDocument;
import com.example.tbkproject.data.enums.OrderStatus;
import com.example.tbkproject.data.enums.PaymentMethod;
import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.data.repositories.OrderRepository;
import com.example.tbkproject.data.repositories.PaymentRepository;
import com.example.tbkproject.dto.general.dtos.CreatePaymentDto;
import com.example.tbkproject.dto.general.dtos.MenusDto;
import com.example.tbkproject.dto.general.dtos.PaymentDto;
import com.example.tbkproject.dto.product.dtos.ProductDto;
import com.example.tbkproject.exceptions.exception.order.OrderWithSessionIdNotFoundException;
import com.example.tbkproject.mapper.PaymentMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    public void createOrderPayment(CreatePaymentDto dto, HttpServletRequest request) {
        String currentSessionId = getSessionInfo(request);
        OrderDocument order = orderRepository.findBySessionId(currentSessionId).orElseThrow(() -> new OrderWithSessionIdNotFoundException(currentSessionId));
        PaymentDocument paymentDocument = new PaymentDocument(order.getId(), dto.getPaymentMethod(), order.getTotalPrice());

        paymentRepository.save(paymentDocument);
        if (!dto.getPaymentMethod().equals(PaymentMethod.CASH)) {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
    }

    public String startSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return session.getId();
    }

    public void endSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    public String getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return session.getId();
        } else {
            return "No existing session.";
        }

    }

}
