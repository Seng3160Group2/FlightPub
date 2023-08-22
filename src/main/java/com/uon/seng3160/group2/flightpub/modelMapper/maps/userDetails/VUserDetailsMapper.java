package com.uon.seng3160.group2.flightpub.modelMapper.maps.userDetails;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.controller.viewModels.userDetails.UserDetailsViewModel;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.userDetails.UserDetailsModel;


@Component
public class VUserDetailsMapper extends GenericMapper {

	public VUserDetailsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public UserDetailsViewModel map(UserDetailsModel entity) {
		return super.map(entity, UserDetailsViewModel.class);
	}
	
}
