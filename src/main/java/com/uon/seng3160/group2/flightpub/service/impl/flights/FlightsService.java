package com.uon.seng3160.group2.flightpub.service.impl.flights;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.dao.repositories.flights.IFlightsRepository;
import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.exceptions.ApplicationException;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;
import com.uon.seng3160.group2.flightpub.service.flights.IFlightsService;


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
