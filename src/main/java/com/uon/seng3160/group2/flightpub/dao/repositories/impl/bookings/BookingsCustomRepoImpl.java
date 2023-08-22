package com.hossein.FlightBooking.dao.repositories.impl.bookings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hossein.FlightBooking.controllers.viewModels.bookings.BookingsViewModel;
import com.hossein.FlightBooking.dao.config.GenericRepository;
import com.hossein.FlightBooking.dto.bookings.BookingsDto;
import com.hossein.FlightBooking.models.bookings.Bookings;

@Repository
public class BookingsCustomRepoImpl extends GenericRepository implements BookingsCustomRepo {

	@Override
	public List<BookingsViewModel> getAll(BookingsDto data) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select"
				+ " e.id as id, user.id as userId, flight.id as flightId, e.persons as persons,"
				+ " flight.originCountry as flightOriginCountry, flight.originCity as flightOriginCity,"
				+ " flight.destinationCountry as flightDestinationCountry, flight.destinationCity as flightDestinationCity,"
				+ " flight.flightNumber as flightNumber, flight.flightDate as flightDate, flight.flightTime as flightTime,"
				+ " ((e.persons + 1) * flight.price) as totalPrice"
				+ " from ")
				.append(Bookings.class.getName())
				.append(" e"
				+ " left join UserModel user on user.id = e.user.id"
				+ " left join Flights flight on flight.id = e.flight.id"
				+ " where 1=1");
		
		if (data.getId() != null && data.getId() > -1) {
			hql.append(" and e.id = :id");
			params.put("id", data.getId());
		}
		
		if (data.getUserId() != null && data.getUserId() > -1) {
			hql.append(" and user.id = :userId");
			params.put("userId", data.getUserId());
		}
		
		if (data.getFlightId() != null && data.getFlightId() > -1) {
			hql.append(" and flight.id = :flightId");
			params.put("flightId", data.getFlightId());
		}
		
		if (data.getPersonNum() != null && data.getPersonNum() > -1) {
			hql.append(" and e.persons = :persons");
			params.put("persons", data.getPersonNum());
		}
		
		return super.getAll(hql.toString(), params, BookingsViewModel.class);
	}

}
