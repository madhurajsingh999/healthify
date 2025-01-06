package com.healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwaggerDock
@EnableServiceSecurity
@EnableDiscoveryClient
@SpringBootApplication
public class HealthifyChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthifyChallengeApplication.class, args);
    }

}