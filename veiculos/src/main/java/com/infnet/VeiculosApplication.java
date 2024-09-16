package com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VeiculosApplication {
    public static void main(String[] args) {
        SpringApplication.run(VeiculosApplication.class, args);
    }
}