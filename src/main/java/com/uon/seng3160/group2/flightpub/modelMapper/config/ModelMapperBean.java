package com.uon.seng3160.group2.flightpub.modelMapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
