package com.example.tbkproject;

import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.data.documents.support.AddOn;
import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.documents.UserDocument;
import com.example.tbkproject.data.repositories.ProductRepository;
import com.example.tbkproject.data.repositories.TableRepository;
import com.example.tbkproject.service.UserService;
import com.mongodb.client.MongoClient;
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
    private final TableRepository tableRepository;
    private final UserService userService;

    @Bean
    CommandLineRunner initMongoDb(MongoTemplate mongoTemplate, MongoClient mongo) {

        mongoTemplate.getDb().getCollection("productDocument").drop();
        mongoTemplate.getDb().getCollection("userDocument").drop();
        mongoTemplate.getDb().getCollection("tableDocument").drop();

        return args -> {
            ProductDocument product1 = new ProductDocument("Cheeseburger", "Burger z serem", "", ProductType.BURGER, 6.99,
                    true, List.of("wołowina", "ser", "sałata"), List.of("laktoza"),
                    550, List.of(new AddOn("Ser", 2.0), new AddOn("Mięso", 3.0)));

            ProductDocument product2 = new ProductDocument(
                    "Frytki", "Crispy golden french fries.", "",
                    ProductType.SNACK, 5.99, true,
                    List.of("ziemniaki", "sól", "olej"),
                    List.of(), 300, List.of(new AddOn("Ketchup", 1.0), new AddOn("Majonez", 1.0))
            );

            ProductDocument product3 = new ProductDocument(
                    "Coca Cola", "Refreshing cold drink.", "",
                    ProductType.DRINK, 3.99, true,
                    List.of("woda", "cukier", "barwnik"),
                    List.of(), 150, List.of()
            );

            ProductDocument product4 = new ProductDocument(
                    "Caesar Salad", "Light Caesar salad with fresh veggies.", "",
                    ProductType.SALAD, 7.99, true,
                    List.of("sałata", "parmezan", "kurczak", "sos caesar"),
                    List.of("laktoza"), 200,
                    List.of(new AddOn("Parmezan", 1.5), new AddOn("Grzanki", 1.0))
            );

            ProductDocument product5 = new ProductDocument(
                    "Lody waniliowe", "Creamy vanilla ice cream with a rich flavor.", "",
                    ProductType.DESERT, 4.99, true,
                    List.of("mleko", "cukier", "wanilia"),
                    List.of("laktoza"), 200,
                    List.of(new AddOn("Bita śmietana", 1.0), new AddOn("Sos czekoladowy", 1.5))
            );

            ProductDocument product6 = new ProductDocument(
                    "Zestaw", "A combo meal with a burger, fries, and drink.", "",
                    ProductType.MEAL, 20.99, true,
                    List.of("wołowina", "ser", "sałata", "pomidor", "bułka", "ziemniaki", "napój"),
                    List.of("laktoza", "gluten"), 1200,
                    List.of(new AddOn("Extra Sauce", 1.0), new AddOn("Double Fries", 2.5))
            );

            TableDocument table1 = new TableDocument(1);
            TableDocument table2 = new TableDocument(2);
            TableDocument table3 = new TableDocument(3);
            TableDocument table4 = new TableDocument(4);
            TableDocument table5 = new TableDocument(5);



            UserDocument admin = new UserDocument("Admin", "admin@gmail.com", "123", true);


            List<ProductDocument> products = List.of(product1, product2, product3, product4, product5, product6);
            List<TableDocument> tables = List.of(table1, table2, table3, table4, table5);
            productRepository.saveAll(products);
            tableRepository.saveAll(tables);
            userService.createUser(admin);
        };
    }


}
