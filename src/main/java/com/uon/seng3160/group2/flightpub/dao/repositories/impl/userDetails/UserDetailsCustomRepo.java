package com.uon.seng3160.group2.flightpub.dao.repositories.impl.userDetails;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.userDetails.UserDetailsViewModel;
import com.uon.seng3160.group2.flightpub.dto.userDetails.UserDetailsDto;


public interface UserDetailsCustomRepo {

	List<UserDetailsViewModel> getAll(UserDetailsDto data);
}
