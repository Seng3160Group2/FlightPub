package com.uon.seng3160.group2.flightpub.modelMapper.typeMaps.bookings;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;


@Configuration
public class DtoToEntityBookings extends GenericMapper {

	public DtoToEntityBookings(ModelMapper mapper) {
		super(mapper);
	}
	
	@Bean
	public TypeMap<BookingsDto, Bookings> dToEBookings() {
		TypeMap<BookingsDto, Bookings> propMap = super.mapProperties(BookingsDto.class, Bookings.class);
		propMap.addMapping(BookingsDto::getId, Bookings::setId);
		propMap.addMapping(BookingsDto::getUserId, (dest, v) -> dest.getUser().setId((Long) v));
		propMap.addMapping(BookingsDto::getFlightId, (dest, v) -> dest.getFlight().setId((Long) v));
		propMap.addMapping(BookingsDto::getPersonNum, Bookings::setPersons);
		return propMap;
	}

}
