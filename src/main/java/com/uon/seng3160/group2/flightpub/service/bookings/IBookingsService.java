package com.hossein.FlightBooking.services.bookings;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.bookings.BookingsViewModel;
import com.hossein.FlightBooking.controllers.viewModels.companions.CompanionsViewModel;
import com.hossein.FlightBooking.dto.bookings.BookingsDto;
import com.hossein.FlightBooking.dto.companions.CompanionsDto;
import com.hossein.FlightBooking.models.bookings.Bookings;
import com.hossein.FlightBooking.models.companions.Companions;

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
