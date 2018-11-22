package com.efive.VisitorManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@PropertySource("classpath:serverpath.properties")
public class VisitorManagementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VisitorManagementApplication.class, args);
	}
}
