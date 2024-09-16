package com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StatusCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatusCheckerApplication.class, args);
    }
}