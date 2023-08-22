package com.hossein.FlightBooking.dao.repositories.impl.companions;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.companions.CompanionsViewModel;
import com.hossein.FlightBooking.dto.companions.CompanionsDto;
import com.hossein.FlightBooking.models.companions.Companions;

public interface CompanionsCustomRepo {

	List<CompanionsViewModel> getAll(CompanionsDto data);
	
	CompanionsViewModel loadByCompanionIdAndUserId(Long companionId, Long bookingId, Long userId);
	
	Long getCountByBookingId(Long bookingId);
}
