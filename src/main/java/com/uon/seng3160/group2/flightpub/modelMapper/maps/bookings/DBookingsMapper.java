package com.hossein.FlightBooking.modelMapper.maps.bookings;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.dto.bookings.BookingsDto;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.bookings.Bookings;

@Component
public class DBookingsMapper extends GenericMapper {

	public DBookingsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public Bookings map(BookingsDto data) {
		return super.map(data, Bookings.class);
	}
	
}
