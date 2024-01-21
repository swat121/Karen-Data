package com.karen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KarenDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarenDataApplication.class, args);
    }

}
