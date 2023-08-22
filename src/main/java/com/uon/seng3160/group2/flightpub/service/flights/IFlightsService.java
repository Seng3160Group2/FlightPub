package com.hossein.FlightBooking.services.flights;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.flights.FlightsViewModel;
import com.hossein.FlightBooking.dto.flights.FlightsDto;
import com.hossein.FlightBooking.models.flights.Flights;

public interface IFlightsService {
	
	List<FlightsViewModel> getAll(FlightsDto data);
	
	Flights loadById(Long id);
	
	long saveEntity(Flights entity);
	
	Boolean deleteEntity(Long id);
}
