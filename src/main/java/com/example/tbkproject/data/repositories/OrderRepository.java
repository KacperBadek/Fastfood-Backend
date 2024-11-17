package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {

    Optional<OrderDocument> findByOrderNumber(int orderNumber);
    Optional<OrderDocument> findFirstByOrderByOrderNumberDesc();


}
