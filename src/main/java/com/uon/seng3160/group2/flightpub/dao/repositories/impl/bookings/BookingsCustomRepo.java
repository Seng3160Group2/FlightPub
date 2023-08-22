package com.hossein.FlightBooking.dao.repositories.impl.bookings;

import java.util.List;

import com.hossein.FlightBooking.controllers.viewModels.bookings.BookingsViewModel;
import com.hossein.FlightBooking.dto.bookings.BookingsDto;

public interface BookingsCustomRepo {

	List<BookingsViewModel> getAll(BookingsDto data);
}
