package com.healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwaggerDock
@EnableServiceSecurity
@SpringBootApplication
public class HealthifyNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthifyNotificationApplication.class, args);
	}

}
