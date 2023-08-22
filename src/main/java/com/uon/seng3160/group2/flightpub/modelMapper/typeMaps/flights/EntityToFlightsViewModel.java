package com.uon.seng3160.group2.flightpub.modelMapper.typeMaps.flights;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;
import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;



@Configuration
public class EntityToFlightsViewModel extends GenericMapper {

	public EntityToFlightsViewModel(ModelMapper mapper) {
		super(mapper);
	}
	
	@Bean
	public TypeMap<Flights, FlightsViewModel> eToVFlights() {
		TypeMap<Flights, FlightsViewModel> propMap = super.mapProperties(Flights.class, FlightsViewModel.class);
		propMap.addMapping(Flights::getId, FlightsViewModel::setId);
		propMap.addMapping(Flights::getOriginCountry, FlightsViewModel::setOriginCountry);
		propMap.addMapping(Flights::getOriginCity, FlightsViewModel::setOriginCity);
		propMap.addMapping(Flights::getDestinationCountry, FlightsViewModel::setDestinationCountry);
		propMap.addMapping(Flights::getDestinationCity, FlightsViewModel::setDestinationCity);
		propMap.addMapping(Flights::getFlightNumber, FlightsViewModel::setFlightNumber);
		propMap.addMapping(Flights::getFlightDate, FlightsViewModel::setFlightDate);
		propMap.addMapping(Flights::getFlightTime, FlightsViewModel::setFlightTime);
		propMap.addMapping(Flights::getPrice, FlightsViewModel::setPrice);
		return propMap;
	}

}
