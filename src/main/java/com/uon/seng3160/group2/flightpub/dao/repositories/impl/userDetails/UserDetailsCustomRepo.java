package com.hossein.FlightBooking.dao.repositories.impl.userDetails;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.userDetails.UserDetailsViewModel;
import com.hossein.FlightBooking.dto.userDetails.UserDetailsDto;

public interface UserDetailsCustomRepo {

	List<UserDetailsViewModel> getAll(UserDetailsDto data);
}
