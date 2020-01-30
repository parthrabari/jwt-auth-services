package com.mastercard.imdbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImdbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImdbServiceApplication.class, args);
    }

}
