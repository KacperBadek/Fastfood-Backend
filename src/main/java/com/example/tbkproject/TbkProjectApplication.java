package com.example.tbkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TbkProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbkProjectApplication.class, args);
    }

}
