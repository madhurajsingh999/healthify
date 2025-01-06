package com.healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableServiceSecurity
@EnableSwaggerDock
@EnableDiscoveryClient
@SpringBootApplication
public class HealthifyRewardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthifyRewardApplication.class, args);
	}

}
