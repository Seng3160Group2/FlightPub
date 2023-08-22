package com.hossein.FlightBooking.services.userDetails;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.userDetails.UserDetailsViewModel;
import com.hossein.FlightBooking.dto.userDetails.UserDetailsDto;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;

public interface IUserDetailsService {
	List<UserDetailsViewModel> getAll(UserDetailsDto data);
	
	long saveEntity(UserDetailsModel entity);
	
	UserDetailsViewModel loadByUserId(Long id);
}
