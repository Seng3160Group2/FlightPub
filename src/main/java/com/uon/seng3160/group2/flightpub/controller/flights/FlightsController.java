package com.uon.seng3160.group2.flightpub.controller.flights;

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

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.flights.DFlightsMapper;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.flights.VFlightsMapper;
import com.uon.seng3160.group2.flightpub.service.flights.IFlightsService;


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
