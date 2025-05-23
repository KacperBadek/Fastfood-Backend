package com.example.tbkproject.data.repositories;

import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);

}
