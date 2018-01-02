package com.fabianpoels.opdracht01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Opdracht01Application {

	public static void main(String[] args) {
		SpringApplication.run(Opdracht01Application.class, args);
	}
}
