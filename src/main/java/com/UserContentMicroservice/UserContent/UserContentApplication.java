package com.UserContentMicroservice.UserContent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class UserContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserContentApplication.class, args);
	}

}
