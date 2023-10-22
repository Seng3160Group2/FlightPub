package com.uon.seng3160.group2.flightpub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.uon.seng3160.group2.flightpub.converter.FlightEntityToModelConverter;
import com.uon.seng3160.group2.flightpub.formatter.LocalDateTimeFormatter;

@Configuration
@ComponentScan(basePackages = { "com.uon.seng3160.group2.flightpub" })
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    // Free Marker
    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates");
        return configurer;
    }

    @Autowired
    private FlightEntityToModelConverter flightEntityToModelConverter;

    @Autowired
    private LocalDateTimeFormatter localDateTimeFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        // registrar.setDateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        // registrar.registerFormatters(registry);

        registry.addConverter(flightEntityToModelConverter);
        registry.addFormatter(localDateTimeFormatter);
    }
}