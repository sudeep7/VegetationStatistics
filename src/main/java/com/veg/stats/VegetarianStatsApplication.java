package com.veg.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class VegetarianStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VegetarianStatsApplication.class, args);
	}
}