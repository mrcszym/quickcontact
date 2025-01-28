package com.quickcontact.quickcontact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.quickcontact")
@EnableJpaRepositories(basePackages = "com.quickcontact.quickcontact.repositories")
@EntityScan(basePackages = "com.quickcontact.quickcontact.entities")
public class QuickcontactApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickcontactApplication.class, args);
	}

}
