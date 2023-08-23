package com.uon.seng3160.group2.flightpub.modelMapper.maps.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.dto.infrastructure.UserDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;


@Component
public class UserMapper extends GenericMapper {

	public UserMapper(ModelMapper mapper) {
		super(mapper);
	}

	public UserModel map(UserDto userDto) {
		return super.map(userDto, UserModel.class);
	}
	
}
