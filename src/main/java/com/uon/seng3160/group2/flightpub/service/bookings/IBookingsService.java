package com.uon.seng3160.group2.flightpub.service.bookings;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.bookings.BookingsViewModel;
import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;
import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;



public interface IBookingsService {
	
	List<BookingsViewModel> getAll(BookingsDto data);
	
	Bookings loadById(Long id);
	
	long saveEntity(Bookings entity);
	
	Boolean deleteEntity(Long id);
	
	List<CompanionsViewModel> getAllCompanions(CompanionsDto data);
	
	CompanionsViewModel loadCompanion(Long bookingId, Long id);
		
	long saveCompanion(Companions entity);
	
	List<Companions> saveAllCompanions(List<Companions> entityList);

	Boolean deleteCompanion(Long bookingId, Long id);
	
	Boolean deleteAllCompanions(Long bookingId);

}
