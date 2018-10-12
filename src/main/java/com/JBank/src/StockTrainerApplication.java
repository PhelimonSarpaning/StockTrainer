package com.JBank.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockTrainerApplication {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "E://Program Files//selenium-java-3.14.0/geckodriver.exe");
		SpringApplication.run(StockTrainerApplication.class, args);
	}
}
