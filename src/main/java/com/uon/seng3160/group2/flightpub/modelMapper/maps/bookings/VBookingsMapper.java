package com.uon.seng3160.group2.flightpub.modelMapper.maps.bookings;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.controller.viewModels.bookings.BookingsViewModel;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;

@Component
public class VBookingsMapper extends GenericMapper {

	public VBookingsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public BookingsViewModel map(Bookings entity) {
		return super.map(entity, BookingsViewModel.class);
	}
	
}
