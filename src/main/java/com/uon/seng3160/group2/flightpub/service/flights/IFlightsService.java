package com.uon.seng3160.group2.flightpub.service.flights;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;


public interface IFlightsService {
	
	List<FlightsViewModel> getAll(FlightsDto data);
	
	Flights loadById(Long id);
	
	long saveEntity(Flights entity);
	
	Boolean deleteEntity(Long id);
}
