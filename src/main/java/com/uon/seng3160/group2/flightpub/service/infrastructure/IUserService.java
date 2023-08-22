package com.uon.seng3160.group2.flightpub.service.infrastructure;

import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;

public interface IUserService {
	
	long saveEntity(UserModel user);

	Long getLoggedInUser();
	
	UserModel loadById(Long id);
}
