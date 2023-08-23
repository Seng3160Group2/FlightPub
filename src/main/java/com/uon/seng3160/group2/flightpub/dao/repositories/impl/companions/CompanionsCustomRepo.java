package com.uon.seng3160.group2.flightpub.dao.repositories.impl.companions;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;


public interface CompanionsCustomRepo {

	List<CompanionsViewModel> getAll(CompanionsDto data);
	
	CompanionsViewModel loadByCompanionIdAndUserId(Long companionId, Long bookingId, Long userId);
	
	Long getCountByBookingId(Long bookingId);
}
