package com.clip.challenge.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.clip.challenge")
@EnableJpaRepositories("com.clip.challenge.repository")
@EntityScan("com.clip.challenge.model")
public class SpringConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfiguration.class, args);
	}

}
