package com.healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HealthifyAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthifyAuthApplication.class, args);
	}

}
