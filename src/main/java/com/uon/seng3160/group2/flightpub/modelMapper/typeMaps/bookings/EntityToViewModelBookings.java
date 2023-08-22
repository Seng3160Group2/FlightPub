package com.hossein.FlightBooking.modelMapper.typeMaps.bookings;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hossein.FlightBooking.controllers.viewModels.bookings.BookingsViewModel;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.bookings.Bookings;

@Configuration
public class EntityToViewModelBookings extends GenericMapper {

	public EntityToViewModelBookings(ModelMapper mapper) {
		super(mapper);
	}

	@Bean
	public TypeMap<Bookings, BookingsViewModel> eToVBookings() {
		TypeMap<Bookings, BookingsViewModel> propMap = super.mapProperties(Bookings.class, BookingsViewModel.class);
		propMap.addMapping(Bookings::getId, BookingsViewModel::setId);
		propMap.addMapping(src -> src.getUser().getId(), BookingsViewModel::setUserId);
		propMap.addMapping(src -> src.getFlight().getId(), BookingsViewModel::setFlightId);
		propMap.addMapping(src -> src.getFlight().getOriginCountry(), BookingsViewModel::setFlightOriginCountry);
		propMap.addMapping(src -> src.getFlight().getOriginCity(), BookingsViewModel::setFlightOriginCity);
		propMap.addMapping(src -> src.getFlight().getDestinationCountry(), BookingsViewModel::setFlightDestinationCountry);
		propMap.addMapping(src -> src.getFlight().getDestinationCity(), BookingsViewModel::setFlightDestinationCity);
		propMap.addMapping(src -> src.getFlight().getFlightNumber(), BookingsViewModel::setFlightNumber);
		propMap.addMapping(src -> src.getFlight().getFlightDate(), BookingsViewModel::setFlightDate);
		propMap.addMapping(src -> src.getFlight().getFlightTime(), BookingsViewModel::setFlightTime);
		propMap.addMapping(Bookings::getTotalPrice, BookingsViewModel::setTotalPrice);
		propMap.addMapping(Bookings::getPersons, BookingsViewModel::setPersons);
		return propMap;
	}
	
}
