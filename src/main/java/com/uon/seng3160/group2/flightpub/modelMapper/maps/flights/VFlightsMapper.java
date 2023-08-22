package com.hossein.FlightBooking.modelMapper.maps.flights;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.controllers.viewModels.flights.FlightsViewModel;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.flights.Flights;

@Component
public class VFlightsMapper extends GenericMapper {

	public VFlightsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public FlightsViewModel map(Flights entity) {
		return super.map(entity, FlightsViewModel.class);
	}

}
