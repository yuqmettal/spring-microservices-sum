package com.yuqmettal.sum.sumservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumServiceApplication.class, args);
	}

}
