package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.AddOnDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AddOnRepository extends MongoRepository<AddOnDocument, String> {
    Optional<AddOnDocument> findByName(String name);
}
