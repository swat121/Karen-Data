package com.karen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DateConfig {
    @Bean
    public Date dateBean() {
        return new Date();
    }
}
