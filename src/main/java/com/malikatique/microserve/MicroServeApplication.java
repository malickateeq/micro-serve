package com.malikatique.microserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class MicroServeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroServeApplication.class, args);
	}
}
