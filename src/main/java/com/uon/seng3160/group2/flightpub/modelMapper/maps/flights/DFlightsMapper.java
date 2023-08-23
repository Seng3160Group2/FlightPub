package com.uon.seng3160.group2.flightpub.modelMapper.maps.flights;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;


@Component
public class DFlightsMapper extends GenericMapper {

	public DFlightsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public Flights map(FlightsDto data) {
		return super.map(data, Flights.class);
	}

}
