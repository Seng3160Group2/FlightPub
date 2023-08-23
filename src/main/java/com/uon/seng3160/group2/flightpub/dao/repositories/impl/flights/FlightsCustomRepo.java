package com.uon.seng3160.group2.flightpub.dao.repositories.impl.flights;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;


public interface FlightsCustomRepo {

	List<FlightsViewModel> getAll(FlightsDto data);
}
