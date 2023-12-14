package com.smilegate.digerapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class  DigerApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigerApiGatewayApplication.class, args);
	}

}
