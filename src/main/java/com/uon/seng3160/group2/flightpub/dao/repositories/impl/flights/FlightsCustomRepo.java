package com.hossein.FlightBooking.dao.repositories.impl.flights;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.flights.FlightsViewModel;
import com.hossein.FlightBooking.dto.flights.FlightsDto;

public interface FlightsCustomRepo {

	List<FlightsViewModel> getAll(FlightsDto data);
}
