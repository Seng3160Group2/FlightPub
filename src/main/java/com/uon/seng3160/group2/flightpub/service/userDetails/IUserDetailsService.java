package com.uon.seng3160.group2.flightpub.service.userDetails;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.userDetails.UserDetailsViewModel;
import com.uon.seng3160.group2.flightpub.dto.userDetails.UserDetailsDto;
import com.uon.seng3160.group2.flightpub.models.userDetails.UserDetailsModel;


public interface IUserDetailsService {
	List<UserDetailsViewModel> getAll(UserDetailsDto data);
	
	long saveEntity(UserDetailsModel entity);
	
	UserDetailsViewModel loadByUserId(Long id);
}
