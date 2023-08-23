package com.uon.seng3160.group2.flightpub.modelMapper.maps.bookings;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;

@Component
public class DBookingsMapper extends GenericMapper {

	public DBookingsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public Bookings map(BookingsDto data) {
		return super.map(data, Bookings.class);
	}
	
}
