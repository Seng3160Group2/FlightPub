package com.hossein.FlightBooking.modelMapper.maps.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.dto.infrastructure.UserDto;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.infrastructure.UserModel;

@Component
public class UserMapper extends GenericMapper {

	public UserMapper(ModelMapper mapper) {
		super(mapper);
	}

	public UserModel map(UserDto userDto) {
		return super.map(userDto, UserModel.class);
	}
	
}
