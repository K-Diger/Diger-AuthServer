package com.smilegate.digereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DigerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigerEurekaApplication.class, args);
    }

}
