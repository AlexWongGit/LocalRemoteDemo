package com.local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.test"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
