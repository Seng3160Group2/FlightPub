package com.uon.seng3160.group2.flightpub.dao.repositories.impl.bookings;

import java.util.List;

import com.uon.seng3160.group2.flightpub.controller.viewModels.bookings.BookingsViewModel;
import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;


public interface BookingsCustomRepo {

	List<BookingsViewModel> getAll(BookingsDto data);
}
