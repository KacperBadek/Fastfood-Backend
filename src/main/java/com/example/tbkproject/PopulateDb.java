package com.example.tbkproject;

import com.example.tbkproject.data.documents.TableDocument;
import com.example.tbkproject.data.enums.ProductType;
import com.example.tbkproject.data.documents.AddOnDocument;
import com.example.tbkproject.data.documents.ProductDocument;
import com.example.tbkproject.data.documents.UserDocument;
import com.example.tbkproject.data.repositories.AddOnRepository;
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
    private final AddOnRepository addOnRepository;
    private final TableRepository tableRepository;
    private final UserService userService;

    @Bean
    CommandLineRunner initMongoDb(MongoTemplate mongoTemplate, MongoClient mongo) {

        mongoTemplate.getDb().getCollection("productDocument").drop();
        mongoTemplate.getDb().getCollection("userDocument").drop();
        mongoTemplate.getDb().getCollection("tableDocument").drop();
        mongoTemplate.getDb().getCollection("addOnDocument").drop();
        mongoTemplate.getDb().getCollection("orderDocument").drop();
        mongoTemplate.getDb().getCollection("paymentDocument").drop();

        return args -> {

            AddOnDocument addon1 = new AddOnDocument("Meat", 4.0, 1);
            AddOnDocument addon2 = new AddOnDocument("Cheese", 3.0, 1);
            AddOnDocument addon3 = new AddOnDocument("Cucumber", 2.0, 1);
            AddOnDocument addon4 = new AddOnDocument("Ketchup", 1.0, 0);
            AddOnDocument addon5 = new AddOnDocument("Mustard", 1.0, 0);

            ProductDocument product1 = new ProductDocument("Cheeseburger", "Burger with cheese", "https://cdn.mcdonalds.pl/uploads/20220922160220/cheeseburger.png",
                    ProductType.BURGER, 12.00, true, List.of("beef", "cheese", "lettuce"), List.of("lactose"),
                    550, List.of(addon1, addon2, addon3)
            );

            ProductDocument product2 = new ProductDocument(
                    "Fries", "Salted potato pieces fried in deep oil", "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ec545603-cf4e-48e0-936d-5376ea12fdc0/dfk5m5y-3ca07d66-1b26-4e00-9367-93add799d352.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2VjNTQ1NjAzLWNmNGUtNDhlMC05MzZkLTUzNzZlYTEyZmRjMFwvZGZrNW01eS0zY2EwN2Q2Ni0xYjI2LTRlMDAtOTM2Ny05M2FkZDc5OWQzNTIucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.V8p2MCryEv24FLsesaGeiZsbRQQAd-v2WvYiBTNVnqY",
                    ProductType.SNACK, 7.00, true,
                    List.of("potatoes", "salt", "oil"),
                    List.of(), 300, List.of(addon4, addon5)
            );

            ProductDocument product3 = new ProductDocument(
                    "Coca Cola", "Refreshing cold drink.", "https://icons.veryicon.com/png/Food%20%26%20Drinks/Coke%20%26%20Pepsi%20Can/Coca%20Cola%20Can.png",
                    ProductType.DRINK, 5.00, true,
                    List.of("water", "sugar", "coloring"),
                    List.of(), 150, List.of()
            );

            ProductDocument product4 = new ProductDocument(
                    "Caesar Salad", "Light Caesar salad with fresh veggies.", "https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kMXWhRwj/200/200/original?country=tt",
                    ProductType.SALAD, 9.00, true,
                    List.of("lettuce", "parmesan", "chicken", "caesar dressing"),
                    List.of("lactose"), 200,
                    List.of()
            );

            ProductDocument product5 = new ProductDocument(
                    "Vanilla Ice Cream", "Creamy vanilla ice cream with a rich flavor.", "https://www.pngkey.com/png/full/32-322082_ice-cream-cones-mcdonalds-vanilla-ice-cream-cone.png",
                    ProductType.DESERT, 6.00, true,
                    List.of("milk", "sugar", "vanilla"),
                    List.of("lactose"), 200,
                    List.of()
            );

            ProductDocument product6 = new ProductDocument(
                    "Combo Meal", "A combo meal with a burger, fries, cola, and ice cream.", "https://wallpapers.com/images/hd/mc-donalds-meal-combo-set-be71491v243lfu2x.png",
                    ProductType.MEAL, 25.00, true,
                    List.of("beef", "cheese", "lettuce", "tomato", "bun", "potatoes", "drink"),
                    List.of("lactose", "gluten"), 1200,
                    List.of(addon1, addon2, addon3, addon4, addon5)
            );

            TableDocument table1 = new TableDocument(1);
            TableDocument table2 = new TableDocument(2);
            TableDocument table3 = new TableDocument(3);
            TableDocument table4 = new TableDocument(4);
            TableDocument table5 = new TableDocument(5);

            UserDocument admin = new UserDocument("Admin", "admin@gmail.com", "123", true);

            List<AddOnDocument> addOns = List.of(addon1, addon2, addon3, addon4, addon5);
            List<ProductDocument> products = List.of(product1, product2, product3, product4, product5, product6);
            List<TableDocument> tables = List.of(table1, table2, table3, table4, table5);
            addOnRepository.saveAll(addOns);
            productRepository.saveAll(products);
            tableRepository.saveAll(tables);
            userService.createUser(admin);
        };
    }


}
