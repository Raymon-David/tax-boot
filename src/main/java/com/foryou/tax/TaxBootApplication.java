package com.foryou.tax;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class TaxBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxBootApplication.class, args);
	}

}
