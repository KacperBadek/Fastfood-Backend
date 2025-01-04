package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentDocument, String> {
    Optional<PaymentDocument> findByOrderId(String orderId);
}
