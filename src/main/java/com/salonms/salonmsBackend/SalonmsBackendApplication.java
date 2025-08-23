package com.salonms.salonmsBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SalonmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonmsBackendApplication.class, args);
	}

}
