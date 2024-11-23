package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentDocument, String> {
}
