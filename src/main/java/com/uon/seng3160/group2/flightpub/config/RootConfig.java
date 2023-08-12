package com.uon.seng3160.group2.flightpub.config;

import java.time.format.DateTimeFormatter;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
@EnableJpaRepositories(basePackages = { "com.uon.seng3160.group2.flightpub.repository" })
public class RootConfig {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new PascalCasePhysicalNamingStrategy();
    }

    @Bean
    public FormattingConversionService conversionService() {

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();

        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        dateTimeRegistrar.registerFormatters(conversionService);

        return conversionService;
    }

    // Other beans and configurations can be defined here
}
