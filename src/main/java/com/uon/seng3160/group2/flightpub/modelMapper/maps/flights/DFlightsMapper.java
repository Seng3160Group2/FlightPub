package com.hossein.FlightBooking.modelMapper.maps.flights;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.dto.flights.FlightsDto;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.flights.Flights;

@Component
public class DFlightsMapper extends GenericMapper {

	public DFlightsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public Flights map(FlightsDto data) {
		return super.map(data, Flights.class);
	}

}
