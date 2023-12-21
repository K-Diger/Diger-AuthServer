package com.smilegate.digerconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DigerConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigerConfigServerApplication.class, args);
    }

}
