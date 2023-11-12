package com.daon.arikkari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class ArikkariApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArikkariApplication.class, args);
	}

}
