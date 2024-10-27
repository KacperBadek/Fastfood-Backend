package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, String> {
}
