package com.hossein.FlightBooking.modelMapper.maps.bookings;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.controllers.viewModels.bookings.BookingsViewModel;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.bookings.Bookings;

@Component
public class VBookingsMapper extends GenericMapper {

	public VBookingsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public BookingsViewModel map(Bookings entity) {
		return super.map(entity, BookingsViewModel.class);
	}
	
}
