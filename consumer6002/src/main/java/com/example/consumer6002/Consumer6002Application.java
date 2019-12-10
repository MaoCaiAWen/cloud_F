package com.example.consumer6002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Consumer6002Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer6002Application.class, args);
    }

}
