package com.uon.seng3160.group2.flightpub.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new PascalCasePhysicalNamingStrategy();
    }

    // Other beans and configurations can be defined here
}
