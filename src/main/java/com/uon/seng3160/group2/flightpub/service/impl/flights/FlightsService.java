package com.hossein.FlightBooking.services.impl.flights;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hossein.FlightBooking.controllers.viewModels.flights.FlightsViewModel;
import com.hossein.FlightBooking.dao.repositories.flights.IFlightsRepository;
import com.hossein.FlightBooking.dto.flights.FlightsDto;
import com.hossein.FlightBooking.exceptions.ApplicationException;
import com.hossein.FlightBooking.models.flights.Flights;
import com.hossein.FlightBooking.services.flights.IFlightsService;

@Service
public class FlightsService implements IFlightsService {
	
	@Autowired
	private IFlightsRepository iFlightRepository;

	@Override
	public List<FlightsViewModel> getAll(FlightsDto data) {
		return this.iFlightRepository.getAll(data);
	}

	@Override
	public Flights loadById(Long id) {
		return this.iFlightRepository.findById(id)
				.orElseThrow(() -> new ApplicationException("Flight Not Found"));
	}

	@Override
	@Transactional
	public long saveEntity(Flights entity) {
		return iFlightRepository.save(entity).getId();
	}

	@Override
	@Transactional
	public Boolean deleteEntity(Long id) {
		if (id != null) {
			this.iFlightRepository.deleteById(id);
			Optional<Flights> flight = this.iFlightRepository.findById(id);
			if (flight.isPresent()) return false;
			return true;
		} else {
			throw new ApplicationException("Id Can Not Be Null");
		}
	}

}
