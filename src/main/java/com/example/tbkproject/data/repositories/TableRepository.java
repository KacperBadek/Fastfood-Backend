package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.TableDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends MongoRepository<TableDocument, String> {

    Optional<TableDocument> findByOrderId(String orderId);
    Optional<TableDocument> findByTableNumber(Integer tableNumber);

}
