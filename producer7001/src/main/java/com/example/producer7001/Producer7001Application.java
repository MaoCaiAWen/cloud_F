package com.example.producer7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Producer7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer7001Application.class, args);
    }

}
