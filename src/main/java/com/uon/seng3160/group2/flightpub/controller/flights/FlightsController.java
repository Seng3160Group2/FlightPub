package com.hossein.FlightBooking.controllers.flights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hossein.FlightBooking.controllers.viewModels.flights.FlightsViewModel;
import com.hossein.FlightBooking.dto.flights.FlightsDto;
import com.hossein.FlightBooking.modelMapper.maps.flights.DFlightsMapper;
import com.hossein.FlightBooking.modelMapper.maps.flights.VFlightsMapper;
import com.hossein.FlightBooking.services.flights.IFlightsService;

@RestController
@RequestMapping("/flightBooking/flights")
public class FlightsController {
	
	@Autowired
	private IFlightsService iFlightService;
	
	@Autowired
	private VFlightsMapper vMapper;
	
	@Autowired
	private DFlightsMapper dMapper;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@PostMapping("/list/grid")
	public List<FlightsViewModel> getAll(@RequestBody FlightsDto data) {
		return this.iFlightService.getAll(data);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@GetMapping("/load/{id}")
	public FlightsViewModel load(@PathVariable Long id) {
		return this.vMapper.map(this.iFlightService.loadById(id));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public long save(@RequestBody FlightsDto data) {
		return this.iFlightService.saveEntity(this.dMapper.map(data));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public Boolean delete(@PathVariable Long id) {
		return this.iFlightService.deleteEntity(id);
	}

}
