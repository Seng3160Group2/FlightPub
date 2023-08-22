package com.uon.seng3160.group2.flightpub.modelMapper.maps.userDetails;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.dto.userDetails.UserDetailsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.userDetails.UserDetailsModel;


@Component
public class UserDetailsMapper extends GenericMapper {

	public UserDetailsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public UserDetailsModel map(UserDetailsDto dto) {
		return super.map(dto, UserDetailsModel.class);
	}

}
