package com.uon.seng3160.group2.flightpub.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.uon.seng3160.group2.flightpub.repository" })
public class RootConfig {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new PascalCasePhysicalNamingStrategy();
    }

    // Other beans and configurations can be defined here
}
