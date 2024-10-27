package com.example.tbkproject;

import com.example.tbkproject.data.ProductType;
import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PopulateDb {

    private final ProductRepository productRepository;

    @Bean
    CommandLineRunner initMongoDb(MongoTemplate mongoTemplate) {

        mongoTemplate.getDb().getCollection("productDocument").drop();

        return args -> {
            ProductDocument product1 = new ProductDocument("Cheeseburger", ProductType.FOOD, 6.99, true);
            ProductDocument product2 = new ProductDocument("Coca cola", ProductType.DRINK, 9.99, true);
            ProductDocument product3 = new ProductDocument("Duże frytki", ProductType.FOOD, 11.99, true);

            List<ProductDocument> products = List.of(product1, product2, product3);
            productRepository.saveAll(products);
        };
    }


}
