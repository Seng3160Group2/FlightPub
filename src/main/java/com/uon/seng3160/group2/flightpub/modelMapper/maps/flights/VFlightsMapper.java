package com.uon.seng3160.group2.flightpub.modelMapper.maps.flights;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;


@Component
public class VFlightsMapper extends GenericMapper {

	public VFlightsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public FlightsViewModel map(Flights entity) {
		return super.map(entity, FlightsViewModel.class);
	}

}
