package com.androprex.jcagro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
public class JcAgroSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcAgroSpringApplication.class, args);
	}

}
